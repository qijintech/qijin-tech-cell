package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.db.model.FeedComment;

import java.util.List;
import java.util.Map;

public interface CellCommentHelper {
    /**
     * 添加评论
     * @param comment
     * @return
     */
    boolean insertComment(FeedComment comment);
    boolean insertCommentReply(FeedComment comment);

    Integer countFeedComment(Long feedId);
    Integer countCommentReply(Long commentId);

    List<FeedComment> pageFeedComment(Long feedId, Integer pageNo, Integer pageSize);

    List<FeedComment> pageCommentReply(Long commentId, Integer pageNo, Integer pageSize);

    Map<Long, FeedComment> mapComment(List<Long> commentIds);
    Map<Long, List<FeedComment>> mapCommentReplies(List<Long> toCommentIds);
}
