package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.base.FeedItemType;
import tech.qijin.cell.feed.db.dao.FeedByGroupDao;
import tech.qijin.cell.feed.db.dao.FeedImageDao;
import tech.qijin.cell.feed.db.dao.FeedItemDao;
import tech.qijin.cell.feed.db.dao.FeedTopicDao;
import tech.qijin.cell.feed.db.model.*;
import tech.qijin.cell.feed.helper.CellFeedHelper;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellFeedHelperImpl implements CellFeedHelper {
    @Autowired
    private FeedItemDao feedItemDao;
    @Autowired
    private FeedByGroupDao feedByGroupDao;
    @Autowired
    private FeedImageDao feedImageDao;
    @Autowired
    private FeedTopicDao feedTopicDao;

    @Override
    public boolean insertFeed(FeedItem feedItem) {
        return feedItemDao.insertSelective(feedItem) > 0;
    }

    @Override
    public boolean insertFeedGroup(FeedByGroup feedGroup) {
        return feedByGroupDao.insertSelective(feedGroup) > 0;
    }

    @Override
    public boolean insertFeedImages(List<FeedImage> images) {
        return feedImageDao.batchInsert(images) > 0;
    }

    @Override
    public FeedItem getFeedById(Long feedItemId) {
        return feedItemDao.selectByPrimaryKey(feedItemId);
    }

    @Override
    public FeedTopic getTopicById(Integer topicId) {
        return feedTopicDao.selectByPrimaryKey(topicId);
    }

    @Override
    public List<FeedItem> listFeedByIds(List<Long> feedItemIds) {
        if (CollectionUtils.isEmpty(feedItemIds)) return Collections.emptyList();
        FeedItemExample example = new FeedItemExample();
        example.createCriteria()
                .andIdIn(feedItemIds);
        return feedItemDao.selectByExample(example);
    }

    @Override
    public List<FeedByGroup> pageFeedByGroup(Long groupId, Integer pageNo, Integer pageSize) {
        FeedByGroupExample example = new FeedByGroupExample();
        example.setOrderByClause(String.format("by create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
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
    public List<FeedItem> pageFeedByUser(Long userId, FeedItemType type, Integer pageNo, Integer pageSize) {
        FeedItemExample example = new FeedItemExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andTypeEqualTo(type)
                .andUserIdEqualTo(userId);
        return feedItemDao.selectByExample(example);
    }

    @Override
    public List<FeedImage> listFeedImages(List<Long> feedItemIds) {
        if (CollectionUtils.isEmpty(feedItemIds)) return Collections.emptyList();
        FeedImageExample example = new FeedImageExample();
        example.createCriteria()
                .andValidEqualTo(true)
                .andFeedItemIdIn(feedItemIds);
        return feedImageDao.selectByExample(example);
    }

    @Override
    public Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedItemIds) {
        return listFeedImages(feedItemIds).stream()
                .collect(Collectors.groupingBy(FeedImage::getFeedItemId));
    }
}
