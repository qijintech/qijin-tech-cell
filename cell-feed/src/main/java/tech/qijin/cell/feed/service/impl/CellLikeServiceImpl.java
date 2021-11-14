package tech.qijin.cell.feed.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.cell.feed.helper.CellLikeHelper;
import tech.qijin.cell.feed.service.CellLikeService;
import tech.qijin.cell.feed.service.CommonService;
import tech.qijin.util4j.aop.annotation.Log;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellLikeServiceImpl extends CommonService implements CellLikeService {
    @Autowired
    private CellLikeHelper cellLikeHelper;

    @Override
    public FeedLike doLikeFeed(Long feedId, Long userId) {
        FeedLike feedLike = cellLikeHelper.getFeedLike(userId, feedId);
        if (feedLike == null) {
            return cellLikeHelper.addFeedLike(userId, feedId);
        }
        if (feedLike.getValid()) {
            return feedLike;
        }
        feedLike.setValid(true);
        cellLikeHelper.enableFeedLikeById(feedLike.getId());
        return feedLike;
    }

    @Log
    @Override
    public FeedLike cancelLikeFeed(Long feedId, Long userId) {
        FeedLike feedLike = cellLikeHelper.getFeedLike(userId, feedId);
        if (feedLike == null) {
            log.warn("[CellLikeService] feedLike not found");
            return null;
        }
        if (!feedLike.getValid()) {
            return feedLike;
        }
        cellLikeHelper.delFeedLikeById(feedLike.getId());
        feedLike.setValid(false);
        return feedLike;
    }

    @Override
    public CommentLike doLikeComment(Long userId, Long commentId) {
        CommentLike commentLike = cellLikeHelper.getCommentLike(userId, commentId);
        if (commentLike == null) {
            return cellLikeHelper.addCommentLike(userId, commentId);
        }
        if (commentLike.getValid()) {
            return commentLike;
        }
        cellLikeHelper.enableCommentLikeById(commentLike.getId());
        commentLike.setValid(true);
        return commentLike;
    }

    @Override
    public CommentLike cancelLikeComment(Long userId, Long commentId) {
        CommentLike commentLike = cellLikeHelper.getCommentLike(userId, commentId);
        if (commentLike == null) {
            return null;
        }
        if (!commentLike.getValid()) {
            return commentLike;
        }
        cellLikeHelper.delCommentLikeById(commentLike.getId());
        commentLike.setValid(false);
        return commentLike;
    }

    @Override
    public Integer countFeedLike(Long feedId) {
        return cellLikeHelper.countFeedLike(feedId);
    }

    @Override
    public Integer countCommentLike(Long commentId) {
        return cellLikeHelper.countCommentLike(commentId);
    }

    @Override
    public List<FeedLike> pageFeedLike(Long feedId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        return cellLikeHelper.pageFeedLike(feedId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public List<CommentLike> pageCommentLike(Long commentId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        return cellLikeHelper.pageCommentLike(commentId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public boolean hasLikedFeed(Long userId, Long feedId) {
        FeedLike feedLike = cellLikeHelper.getFeedLike(userId, feedId);
        return isLike(feedLike);
    }

    @Override
    public boolean hasLikedComment(Long userId, Long commentId) {
        CommentLike commentLike = cellLikeHelper.getCommentLike(userId, commentId);
        return isLike(commentLike);
    }

    @Override
    public Map<Long, Boolean> mapFeedLike(Long userId, List<Long> feedIds) {
        if (CollectionUtils.isEmpty(feedIds)) return Collections.emptyMap();
        Map<Long, FeedLike> feedLikeMap = cellLikeHelper.mapFeedLike(userId, feedIds);
        if (MapUtils.isEmpty(feedLikeMap)) return Collections.emptyMap();
        return feedLikeMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> isLike(entry.getValue())));
    }

    @Override
    public Map<Long, Boolean> mapCommentLike(Long userId, List<Long> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) return Collections.emptyMap();
        Map<Long, CommentLike> commentLikeMap = cellLikeHelper.mapCommentLike(userId, commentIds);
        if (MapUtils.isEmpty(commentLikeMap)) return Collections.emptyMap();
        return commentLikeMap.entrySet().stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> isLike(entry.getValue())));
    }

    private boolean isLike(FeedLike feedLike) {
        if (feedLike == null) return false;
        return feedLike.getValid();
    }

    private boolean isLike(CommentLike commentLike) {
        if (commentLike == null) return false;
        return commentLike.getValid();
    }
}
