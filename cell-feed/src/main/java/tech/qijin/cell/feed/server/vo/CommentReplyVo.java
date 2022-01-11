package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.service.bo.CommentReplyBo;
import tech.qijin.cell.user.db.model.UserProfile;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class CommentReplyVo {
    private FeedCommentVo comment;
    private FeedCommentVo toComment;

    @Deprecated
    public static CommentReplyVo from(FeedComment comment, FeedComment toComment, UserProfile profile) {
        return CommentReplyVo.builder()
                .comment(FeedCommentVo.from(comment, profile))
                .toComment(FeedCommentVo.from(toComment, profile))
                .build();
    }

    public static CommentReplyVo from(CommentReplyBo replyBo, Map<Long, UserProfile> profileMap) {
        if (replyBo == null) return null;
        FeedCommentVo commentVo = null;
        FeedCommentVo toCommentVo = null;
        if (replyBo.getComment() != null) {
            commentVo = FeedCommentVo.from(replyBo.getComment(), profileMap.get(replyBo.getComment().getUserId()));
        }
        if (replyBo.getToComment() != null) {
            toCommentVo = FeedCommentVo.from(replyBo.getToComment(), profileMap.get(replyBo.getToComment().getUserId()));
        }
        return CommentReplyVo.builder()
                .comment(commentVo)
                .toComment(toCommentVo)
                .build();
    }

    public static List<CommentReplyVo> from(List<CommentReplyBo> replyBos, Map<Long, UserProfile> profileMap) {
        return replyBos.stream()
                .map(replyBo -> CommentReplyVo.from(replyBo, profileMap))
                .collect(Collectors.toList());
    }
}
