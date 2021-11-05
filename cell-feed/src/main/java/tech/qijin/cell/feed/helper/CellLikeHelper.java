package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.FeedLike;

import java.util.List;

public interface CellLikeHelper {
    FeedLike getFeedLike(Long userId, Long feedItemId);

    CommentLike getCommentLike(Long userId, Long commentId);

    boolean addFeedLike(Long userId, Long feedItemId);

    boolean addCommentLike(Long userId, Long commentId);

    boolean enableFeedLikeById(Long id);

    boolean enableCommentLikeById(Long id);

    boolean delFeedLikeById(Long id);

    boolean delCommentLikeById(Long id);

    List<FeedLike> pageFeedLike(Long feedItemId, Integer pageNo, Integer pageSize);

    List<CommentLike> pageCommentLike(Long commentId, Integer pageNo, Integer pageSize);

    Integer countFeedLike(Long feedItemId);

    Integer countCommentLike(Long commentId);
}
