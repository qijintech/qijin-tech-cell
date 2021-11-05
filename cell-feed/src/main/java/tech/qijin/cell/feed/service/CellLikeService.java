package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;

public interface CellLikeService {

    /**
     * 点赞feed
     * @param userId
     * @param feedItemId
     */
    void doLikeFeed(Long userId, Long feedItemId);

    /**
     * 点赞评论
     * @param userId
     * @param commentId
     */
    void doLikeComment(Long userId, Long commentId);

    Integer countFeedLike(Long feedItemId);

    Integer countCommentLike(Long commentId);

    List<FeedLike> pageFeedLike(Long feedItemId, PageVo pageVo);

    List<CommentLike> pageCommentLike(Long commentId, PageVo pageVo);
}
