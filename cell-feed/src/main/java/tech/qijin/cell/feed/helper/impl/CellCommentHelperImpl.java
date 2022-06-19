package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.dao.FeedCommentDao;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedCommentExample;
import tech.qijin.cell.feed.helper.CellCommentHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellCommentHelperImpl implements CellCommentHelper {
    @Autowired
    private FeedCommentDao feedCommentDao;

    @Override
    public boolean insertComment(FeedComment comment) {
        checkComment(comment);
        return feedCommentDao.insertSelective(comment) > 0;
    }

    @Override
    public boolean insertCommentReply(FeedComment comment) {
        checkCommentReply(comment);
        return feedCommentDao.insertSelective(comment) > 0;
    }

    @Override
    public Integer countFeedComment(Long feedId) {
        FeedCommentExample example = new FeedCommentExample();
        FeedCommentExample.Criteria criteria = example.createCriteria();
        criteria.andFeedIdEqualTo(feedId);
        criteria.andValidEqualTo(true);

        return (int) feedCommentDao.countByExample(example);
    }

    @Override
    public Integer countCommentReply(Long commentId) {
        FeedCommentExample example = new FeedCommentExample();
        example.createCriteria()
                .andToCommentIdEqualTo(commentId)
                .andValidEqualTo(true);
        return (int) feedCommentDao.countByExample(example);
    }

    @Override
    public List<FeedComment> pageFeedComment(Long feedId, Integer pageNo, Integer pageSize) {
        FeedCommentExample example = new FeedCommentExample();
        example.setOrderByClause(String.format("create_time asc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        FeedCommentExample.Criteria criteria = example.createCriteria();
        criteria.andFeedIdEqualTo(feedId)
                .andValidEqualTo(true);
        return feedCommentDao.selectByExample(example);
    }

    @Override
    public List<FeedComment> pageCommentReply(Long commentId, Integer pageNo, Integer pageSize) {
        FeedCommentExample example = new FeedCommentExample();
        example.setOrderByClause(String.format("create_time asc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        FeedCommentExample.Criteria criteria = example.createCriteria();
        criteria.andToCommentIdEqualTo(commentId)
                .andValidEqualTo(true);
        return feedCommentDao.selectByExample(example);
    }

    @Override
    public Map<Long, FeedComment> mapComment(List<Long> commentIds) {
        if (CollectionUtils.isEmpty(commentIds)) return Collections.emptyMap();
        FeedCommentExample example = new FeedCommentExample();
        example.createCriteria()
                .andIdIn(commentIds);
        return feedCommentDao.selectByExample(example).stream()
                .collect(Collectors.toMap(FeedComment::getId, Function.identity()));
    }

    @Override
    public Map<Long, List<FeedComment>> mapCommentReplies(List<Long> toCommentIds) {
        if (CollectionUtils.isEmpty(toCommentIds)) return Collections.emptyMap();
        FeedCommentExample example = new FeedCommentExample();
        example.createCriteria()
                .andToCommentIdIn(toCommentIds)
                .andValidEqualTo(true);
        return feedCommentDao.selectByExample(example).stream()
                .collect(Collectors.groupingBy(FeedComment::getToCommentId));
    }

    @Override
    public FeedComment getComment(Long id) {
        return feedCommentDao.selectByPrimaryKey(id);
    }

    @Override
    public boolean delComment(Long id) {
        FeedComment update = new FeedComment();
        update.setId(id);
        update.setValid(false);
        return feedCommentDao.updateByPrimaryKeySelective(update) > 0;
    }

    private void checkComment(FeedComment comment) {
        MAssert.isTrue(comment != null, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(comment.getUserId()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(comment.getFeedId()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(StringUtils.isNotBlank(comment.getContentText())
                || StringUtils.isNotBlank(comment.getContentImage()), ResEnum.INVALID_PARAM);

    }
    private void checkCommentReply(FeedComment comment) {
        MAssert.isTrue(comment != null, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(comment.getUserId()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(StringUtils.isNotBlank(comment.getContentText())
                || StringUtils.isNotBlank(comment.getContentImage()), ResEnum.INVALID_PARAM);

    }
}
