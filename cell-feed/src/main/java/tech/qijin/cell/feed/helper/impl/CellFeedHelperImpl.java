package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.base.FeedType;
import tech.qijin.cell.feed.db.dao.FeedByGroupDao;
import tech.qijin.cell.feed.db.dao.FeedDao;
import tech.qijin.cell.feed.db.dao.FeedImageDao;
import tech.qijin.cell.feed.db.dao.FeedTopicDao;
import tech.qijin.cell.feed.db.model.*;
import tech.qijin.cell.feed.helper.CellFeedHelper;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellFeedHelperImpl implements CellFeedHelper {
    @Autowired
    private FeedDao feedItemDao;
    @Autowired
    private FeedByGroupDao feedByGroupDao;
    @Autowired
    private FeedImageDao feedImageDao;
    @Autowired
    private FeedTopicDao feedTopicDao;

    @Override
    public boolean insertFeed(Feed feed) {
        return feedItemDao.insertSelective(feed) > 0;
    }

    @Override
    public boolean insertFeedGroup(FeedByGroup feedGroup) {
        return feedByGroupDao.insertSelective(feedGroup) > 0;
    }

    @Override
    public boolean batchInsertFeedGroup(List<FeedByGroup> feedGroups) {
        if (CollectionUtils.isEmpty(feedGroups)) return true;
        return feedByGroupDao.batchInsert(feedGroups) > 0;
    }

    @Override
    public long removeFeedGroup(Long userId, Long groupId) {
        FeedByGroup update = new FeedByGroup();
        update.setValid(false);

        FeedByGroupExample example = new FeedByGroupExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andGroupIdEqualTo(groupId);
        return feedByGroupDao.updateByExample(update, example);
    }

    @Override
    public boolean insertFeedImages(List<FeedImage> images) {
        return feedImageDao.batchInsert(images) > 0;
    }

    @Override
    public Feed getFeedById(Long feed) {
        return feedItemDao.selectByPrimaryKey(feed);
    }

    @Override
    public FeedTopic getTopicById(Integer topicId) {
        return feedTopicDao.selectByPrimaryKey(topicId);
    }

    @Override
    public List<Feed> listFeedByIds(List<Long> feeIds) {
        if (CollectionUtils.isEmpty(feeIds)) return Collections.emptyList();
        FeedExample example = new FeedExample();
        example.createCriteria()
                .andIdIn(feeIds);
        return feedItemDao.selectByExample(example);
    }

    @Override
    public Map<Long, Feed> mapFeedByIds(List<Long> feedIds) {
        return listFeedByIds(feedIds).stream()
                .collect(Collectors.toMap(Feed::getId, Function.identity()));
    }

    @Override
    public List<FeedByGroup> pageFeedByGroup(Long groupId, Integer pageNo, Integer pageSize) {
        FeedByGroupExample example = new FeedByGroupExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andGroupIdEqualTo(groupId)
                .andValidEqualTo(true);
        return feedByGroupDao.selectByExample(example);
    }

    @Override
    public List<FeedByGroup> pageFeedByGroups(List<Long> groupIds, Integer pageNo, Integer pageSize) {
        FeedByGroupExample example = new FeedByGroupExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andGroupIdIn(groupIds)
                .andValidEqualTo(true);
        return feedByGroupDao.selectByExample(example);
    }

    @Override
    public List<Feed> pageFeedByUser(Long userId, FeedType type, Integer pageNo, Integer pageSize) {
        FeedExample example = new FeedExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andTypeEqualTo(type)
                .andUserIdEqualTo(userId);
        return feedItemDao.selectByExample(example);
    }

    @Override
    public List<FeedImage> listFeedImages(List<Long> feedIds) {
        if (CollectionUtils.isEmpty(feedIds)) return Collections.emptyList();
        FeedImageExample example = new FeedImageExample();
        example.createCriteria()
                .andValidEqualTo(true)
                .andFeedIdIn(feedIds);
        return feedImageDao.selectByExample(example);
    }

    @Override
    public Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedIds) {
        return listFeedImages(feedIds).stream()
                .collect(Collectors.groupingBy(FeedImage::getFeedId));
    }

    @Override
    public List<FeedTopic> pageFeedTopic(Integer pageNo, Integer pageSize) {
        FeedTopicExample example = new FeedTopicExample();
        example.createCriteria()
                .andValidEqualTo(true);
        return feedTopicDao.selectByExample(example);
    }

}
