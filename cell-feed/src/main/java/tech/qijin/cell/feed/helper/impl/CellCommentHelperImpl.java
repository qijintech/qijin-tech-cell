package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.dao.FeedCommentDao;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedCommentExample;
import tech.qijin.cell.feed.helper.CellCommentHelper;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@Slf4j
@Service
public class CellCommentHelperImpl implements CellCommentHelper {
    @Autowired
    private FeedCommentDao feedCommentDao;

    @Override
    public Integer countComment(Long feedItemId, Long commentId) {
        FeedCommentExample example = new FeedCommentExample();
        FeedCommentExample.Criteria criteria = example.createCriteria()
                .andFeedIdEqualTo(feedItemId);
        if (NumberUtil.gtZero(commentId)) {
            criteria.andToCommentIdEqualTo(commentId);
        }

        return (int)feedCommentDao.countByExample(example);
    }

    @Override
    public List<FeedComment> pageComment(Long feedItemId, Long commentId, Integer pageNo, Integer pageSize) {
        FeedCommentExample example = new FeedCommentExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        FeedCommentExample.Criteria criteria = example.createCriteria()
                .andFeedIdEqualTo(feedItemId);
        if (NumberUtil.gtZero(commentId)) {
            criteria.andToCommentIdEqualTo(commentId);
        }
        return feedCommentDao.selectByExample(example);
    }
}
