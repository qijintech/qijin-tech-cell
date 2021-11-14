package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.FeedLike;

import java.util.List;
import java.util.Map;

public interface CellLikeHelper {
    FeedLike getFeedLike(Long userId, Long feedId);

    CommentLike getCommentLike(Long userId, Long commentId);

    FeedLike addFeedLike(Long userId, Long feedId);

    CommentLike addCommentLike(Long userId, Long commentId);

    boolean enableFeedLikeById(Long id);

    boolean enableCommentLikeById(Long id);

    boolean delFeedLikeById(Long id);

    boolean delCommentLikeById(Long id);

    List<FeedLike> pageFeedLike(Long feedId, Integer pageNo, Integer pageSize);

    List<CommentLike> pageCommentLike(Long commentId, Integer pageNo, Integer pageSize);

    Integer countFeedLike(Long feedId);

    Integer countCommentLike(Long commentId);

    List<FeedLike> listFeedLikeByFeedIds(Long userId, List<Long> feedIds);

    List<CommentLike> listCommentLikeByCommentIds(Long userId, List<Long> commentIds);

    Map<Long, FeedLike> mapFeedLike(Long userId, List<Long> feedIds);

    Map<Long, CommentLike> mapCommentLike(Long userId, List<Long> commentIds);
}
