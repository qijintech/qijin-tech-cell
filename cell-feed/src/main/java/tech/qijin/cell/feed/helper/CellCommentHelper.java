package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.db.model.FeedComment;

import java.util.List;

public interface CellCommentHelper {
    Integer countComment(Long feedItemId, Long commentId);

    List<FeedComment> pageComment(Long feedItemId, Long commentId, Integer pageNo, Integer pageSize);
}
