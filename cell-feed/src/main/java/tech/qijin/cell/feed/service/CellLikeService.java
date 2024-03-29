package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;
import java.util.Map;

public interface CellLikeService {

    /**
     * 点赞feed
     * @param feedId
     * @param userId
     */
    FeedLike doLikeFeed(Long feedId, Long userId);
    FeedLike cancelLikeFeed(Long feedId, Long userId);

    /**
     * 点赞评论
     * @param userId
     * @param commentId
     */
    CommentLike doLikeComment(Long userId, Long commentId);
    CommentLike cancelLikeComment(Long userId, Long commentId);

    Integer countFeedLike(Long feedId);

    Integer countCommentLike(Long commentId);

    List<FeedLike> pageFeedLike(Long feedId, PageVo pageVo);

    List<CommentLike> pageCommentLike(Long commentId, PageVo pageVo);

    boolean hasLikedFeed(Long userId, Long feedId);

    boolean hasLikedComment(Long userId, Long commentId);

    Map<Long, Boolean> mapFeedLike(Long userId, List<Long> feedIds);
    Map<Long, Boolean> mapCommentLike(Long userId, List<Long> commentIds);
}
