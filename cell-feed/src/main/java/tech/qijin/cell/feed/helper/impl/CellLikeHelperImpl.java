package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.dao.CommentLikeDao;
import tech.qijin.cell.feed.db.dao.FeedLikeDao;
import tech.qijin.cell.feed.db.model.CommentLike;
import tech.qijin.cell.feed.db.model.CommentLikeExample;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.cell.feed.db.model.FeedLikeExample;
import tech.qijin.cell.feed.helper.CellLikeHelper;
import tech.qijin.cell.feed.helper.CommonHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellLikeHelperImpl extends CommonHelper implements CellLikeHelper {
    @Autowired
    private FeedLikeDao feedLikeDao;
    @Autowired
    private CommentLikeDao commentLikeDao;

    @Override
    public FeedLike getFeedLike(Long userId, Long feedId) {
        FeedLikeExample example = new FeedLikeExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andFeedIdEqualTo(feedId);
        return feedLikeDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public CommentLike getCommentLike(Long userId, Long commentId) {
        CommentLikeExample example = new CommentLikeExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andCommentIdEqualTo(commentId);
        return commentLikeDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public FeedLike addFeedLike(Long userId, Long feedId) {
        FeedLike record = new FeedLike();
        record.setFeedId(feedId);
        record.setUserId(userId);
        record.setValid(true);
        if (feedLikeDao.insertSelective(record) > 0) {
            return record;
        }
        return null;
    }

    @Override
    public CommentLike addCommentLike(Long userId, Long commentId) {
        CommentLike record = new CommentLike();
        record.setCommentId(commentId);
        record.setUserId(userId);
        record.setValid(true);
        commentLikeDao.insertSelective(record);
        return record;
    }

    @Override
    public boolean enableFeedLikeById(Long id) {
        FeedLike record = new FeedLike();
        record.setId(id);
        record.setValid(true);
        return feedLikeDao.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public boolean enableCommentLikeById(Long id) {
        CommentLike record = new CommentLike();
        record.setId(id);
        record.setValid(true);
        return commentLikeDao.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public boolean delFeedLikeById(Long id) {
        FeedLike record = new FeedLike();
        record.setId(id);
        record.setValid(false);
        return feedLikeDao.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public boolean delCommentLikeById(Long id) {
        CommentLike record = new CommentLike();
        record.setId(id);
        record.setValid(false);
        return commentLikeDao.updateByPrimaryKeySelective(record) > 0;
    }

    @Override
    public List<FeedLike> pageFeedLike(Long feedId, Integer pageNo, Integer pageSize) {
        FeedLikeExample example = new FeedLikeExample();
        example.setOrderByClause(orderBy("create_time", "desc", pageNo, pageSize));
        example.createCriteria()
                .andFeedIdEqualTo(feedId)
                .andValidEqualTo(true);
        return feedLikeDao.selectByExample(example);
    }

    @Override
    public List<CommentLike> pageCommentLike(Long commentId, Integer pageNo, Integer pageSize) {
        CommentLikeExample example = new CommentLikeExample();
        example.setOrderByClause(orderBy("create_time", "desc", pageNo, pageSize));
        example.createCriteria()
                .andCommentIdEqualTo(commentId)
                .andValidEqualTo(true);
        return commentLikeDao.selectByExample(example);
    }

    @Override
    public Integer countFeedLike(Long feedId) {
        FeedLikeExample example = new FeedLikeExample();
        example.createCriteria()
                .andFeedIdEqualTo(feedId)
                .andValidEqualTo(true);
        return (int) feedLikeDao.countByExample(example);
    }

    @Override
    public Integer countCommentLike(Long commentId) {
        CommentLikeExample example = new CommentLikeExample();
        example.createCriteria()
                .andCommentIdEqualTo(commentId)
                .andValidEqualTo(true);
        return (int) commentLikeDao.countByExample(example);
    }

    @Override
    public List<FeedLike> listFeedLikeByFeedIds(Long userId, List<Long> feedIds) {
        if (CollectionUtils.isEmpty(feedIds)) return Collections.emptyList();
        FeedLikeExample example = new FeedLikeExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andFeedIdIn(feedIds);
        return feedLikeDao.selectByExample(example);
    }

    @Override
    public List<CommentLike> listCommentLikeByCommentIds(Long userId, List<Long> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) return Collections.emptyList();
        CommentLikeExample example = new CommentLikeExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andCommentIdIn(commentIds);
        return commentLikeDao.selectByExample(example);
    }

    @Override
    public Map<Long, FeedLike> mapFeedLike(Long userId, List<Long> feedIds) {
        return listFeedLikeByFeedIds(userId, feedIds).stream()
                .collect(Collectors.toMap(FeedLike::getFeedId, Function.identity()));
    }

    @Override
    public Map<Long, CommentLike> mapCommentLike(Long userId, List<Long> commentIds) {
        return listCommentLikeByCommentIds(userId, commentIds).stream()
                .collect(Collectors.toMap(CommentLike::getCommentId, Function.identity()));
    }
}
