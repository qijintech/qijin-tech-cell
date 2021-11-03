package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;

/**
 * 评论相关方法
 */
public interface CellCommentService {
    Integer countComment(Long feedItemId, Long commentId);

    List<FeedComment> pageComment(Long feedItemId, Long commentId, PageVo pageVo);
}
