package tech.qijin.cell.feed.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.service.bo.CommentRepliesBo;
import tech.qijin.cell.feed.service.bo.FeedCommentBo;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.server.vo.ProfileVo;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class FeedCommentVo {
    private Long id;

    private Long feedItemId;

    private Long userId;

    private Long toCommentId;

    private String contentText;

    private String contentImage;

    private ProfileVo profile;

    private String createTime;

    private Integer likeCount;

    private Boolean hasLiked;



    /**
     * 评论的评论
     */
    private List<CommentReplyVo> commentReplies;
    private Integer replyCount;
    private Integer pageNo = 1;

    public static FeedCommentVo from(FeedComment comment, UserProfile profile) {
        FeedCommentVo commentVo = ConvertUtil.convert(comment, FeedCommentVo.class);
        if (commentVo != null) {
            commentVo.setProfile(ProfileVo.from(profile));
            commentVo.setCreateTime(DateUtil.formatSocial(comment.getCreateTime()));
        }
        return commentVo;
    }

    public static List<FeedCommentVo> from(List<FeedComment> comments, Map<Long, UserProfile> profileMap) {
        if (CollectionUtils.isEmpty(comments)) return Collections.emptyList();
        return comments.stream()
                .map(comment -> FeedCommentVo.from(comment, profileMap.get(comment.getUserId())))
                .collect(Collectors.toList());
    }

    public static FeedCommentVo fromBo(FeedCommentBo feedCommentBo, UserProfile profile) {
        if (feedCommentBo == null) return null;
        FeedCommentVo commentVo = ConvertUtil.convert(feedCommentBo.getComment(), FeedCommentVo.class);
        if (commentVo == null) return null;
        commentVo.setProfile(ProfileVo.from(profile));
        commentVo.setHasLiked(feedCommentBo.getHasLiked());
        commentVo.setCreateTime(DateUtil.formatSocial(feedCommentBo.getComment().getCreateTime()));
        commentVo.setHasLiked(feedCommentBo.getHasLiked());
        commentVo.setLikeCount(feedCommentBo.getLikeCount());
        return commentVo;
    }

    public static List<FeedCommentVo> fromBo(List<FeedCommentBo> feedCommentBos, Map<Long, UserProfile> profileMap) {
        if (CollectionUtils.isEmpty(feedCommentBos)) return Collections.emptyList();
        return feedCommentBos.stream()
                .filter(Objects::nonNull)
                .filter(commentBo -> commentBo.getComment() != null)
                .map(commentBo -> FeedCommentVo.fromBo(commentBo, profileMap.get(commentBo.getComment().getUserId())))
                .collect(Collectors.toList());
    }

    public static List<FeedCommentVo> fromBo(List<FeedCommentBo> feedCommentBos, Map<Long, CommentRepliesBo> repliesBoMap, Map<Long, UserProfile> profileMap) {
        if (CollectionUtils.isEmpty(feedCommentBos)) return Collections.emptyList();
        return feedCommentBos.stream()
                .filter(Objects::nonNull)
                .filter(commentBo -> commentBo.getComment() != null)
                .map(commentBo -> {
                    FeedCommentVo commentVo = FeedCommentVo.fromBo(commentBo, profileMap.get(commentBo.getComment().getUserId()));
                    CommentRepliesBo commentRepliesBo = repliesBoMap.get(commentBo.getComment().getId());
                    if (commentRepliesBo != null && CollectionUtils.isNotEmpty(commentRepliesBo.getCommentReplies())) {
                        commentVo.setCommentReplies(CommentReplyVo.from(commentRepliesBo.getCommentReplies(), profileMap));
                        commentVo.setReplyCount(commentRepliesBo.getReplyCount());
                    }
                    return commentVo;
                })
                .collect(Collectors.toList());
    }
}
