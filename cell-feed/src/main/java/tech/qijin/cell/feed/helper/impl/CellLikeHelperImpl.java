package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
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

import java.util.List;

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
    public boolean addFeedLike(Long userId, Long feedId) {
        FeedLike record = new FeedLike();
        record.setFeedId(feedId);
        record.setUserId(userId);
        record.setValid(true);
        return feedLikeDao.insertSelective(record) > 0;
    }

    @Override
    public boolean addCommentLike(Long userId, Long commentId) {
        CommentLike record = new CommentLike();
        record.setCommentId(commentId);
        record.setUserId(userId);
        record.setValid(true);
        return commentLikeDao.insertSelective(record) > 0;
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
        example.setOrderByClause(orderBy("create_time", "asc", pageNo, pageSize));
        example.createCriteria()
                .andFeedIdEqualTo(feedId)
                .andValidEqualTo(true);
        return feedLikeDao.selectByExample(example);
    }

    @Override
    public List<CommentLike> pageCommentLike(Long commentId, Integer pageNo, Integer pageSize) {
        CommentLikeExample example = new CommentLikeExample();
        example.setOrderByClause(orderBy("create_time", "asc", pageNo, pageSize));
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
}
