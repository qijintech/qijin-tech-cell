package tech.qijin.cell.feed.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.cell.feed.helper.CellLikeHelper;
import tech.qijin.cell.feed.service.CellLikeService;
import tech.qijin.cell.feed.service.CommonService;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;

@Slf4j
@Service
public class CellLikeServiceImpl extends CommonService implements CellLikeService {
    @Autowired
    private CellLikeHelper cellLikeHelper;

    @Override
    public void doLikeFeed(Long userId, Long feedItemId) {
        FeedLike feedLike = cellLikeHelper.getFeedLike(userId, feedItemId);
        if (feedLike == null) {
            cellLikeHelper.addFeedLike(userId, feedItemId);
            return;
        }
        if (feedLike.getValid()) {
            return;
        }
        cellLikeHelper.enableFeedLikeById(feedLike.getId());
    }

    @Override
    public void doLikeComment(Long userId, Long commentId) {
        CommentLike commentLike = cellLikeHelper.getCommentLike(userId, commentId);
        if (commentLike == null) {
            cellLikeHelper.addCommentLike(userId, commentId);
            return;
        }
        if (commentLike.getValid()) {
            return;
        }
        cellLikeHelper.enableCommentLikeById(commentLike.getId());
    }

    @Override
    public Integer countFeedLike(Long feedItemId) {
        return cellLikeHelper.countFeedLike(feedItemId);
    }

    @Override
    public Integer countCommentLike(Long commentId) {
        return cellLikeHelper.countCommentLike(commentId);
    }

    @Override
    public List<FeedLike> pageFeedLike(Long feedItemId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        return cellLikeHelper.pageFeedLike(feedItemId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public List<CommentLike> pageCommentLike(Long commentId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        return cellLikeHelper.pageCommentLike(commentId, pageVo.getPageNo(), pageVo.getPageSize());
    }
}
