package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * 评论相关方法
 */
public interface CellCommentService {
    /**
     * 添加评论
     * @param userId
     * @param feedId
     * @param commentText
     * @param commentImage
     * @return
     */
    FeedComment addFeedComment(Long userId, Long feedId, String commentText, String commentImage);

    /**
     * 评论回复
     * @param userId
     * @param feedId
     * @param commentId
     * @param commentText
     * @param commentImage
     * @return
     */
    FeedComment replyComment(Long userId,
                         Long feedId,
                         Long commentId,
                         Long subCommentId,
                         String commentText,
                         String commentImage);
    /**
     * 返回评论数量
     * @param feeId
     * @return
     */
    Integer countFeedComment(Long feeId);
    Integer countCommentReply(Long commentId);

    /**
     * 分页查询评论列表
     * @param feedId
     * @param pageVo
     * @return
     */
    List<FeedComment> pageFeedComment(Long feedId, PageVo pageVo);

    /**
     * 查询评论的评论
     * @param commentId
     * @param pageVo
     * @return
     */
    List<FeedComment> pageCommentReply(Long commentId, PageVo pageVo);

    Map<Long, FeedComment> mapComment(List<Long> commentIds);

    Map<Long, List<FeedComment>> mapCommentReplies(List<Long> toCommentIds);

    FeedComment getCommentById(Long commentId);
}
