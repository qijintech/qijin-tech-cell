package tech.qijin.cell.feed.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.cell.feed.base.FeedItemBo;
import tech.qijin.cell.feed.base.FeedItemType;
import tech.qijin.cell.feed.db.model.FeedByGroup;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedItem;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.helper.CellFeedHelper;
import tech.qijin.cell.feed.service.CellFeedService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellFeedServiceImpl implements CellFeedService {
    private Integer defaultPageSize = 10;
    @Autowired
    private CellFeedHelper cellFeedHelper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createFeedByGroup(FeedItemBo feedItemBo) {
        checkFeedItem(feedItemBo);
        FeedItem feedItem = feedItemBo.getFeedItem();
        List<Long> groupIds = feedItemBo.getGroupIds();
        feedItem.setType(FeedItemType.PUBLISHED);

        // 插入feed
        MAssert.isTrue(cellFeedHelper.insertFeed(feedItem), ResEnum.BAD_GATEWAY);
        // 插入图片
        if (CollectionUtils.isNotEmpty(feedItemBo.getImages())) {
            List<FeedImage> images = feedItemBo.getImages().stream().map(image -> {
                FeedImage feedImage = new FeedImage();
                feedImage.setUrl(image);
                feedImage.setFeedItemId(feedItem.getId());
                return feedImage;
            }).collect(Collectors.toList());
            MAssert.isTrue(cellFeedHelper.insertFeedImages(images), ResEnum.BAD_GATEWAY);
        }
        // 按group插入
        groupIds.stream().forEach(groupId -> {
            FeedByGroup group = new FeedByGroup();
            group.setFeedItemId(feedItem.getId());
            group.setGroupId(groupId);
            MAssert.isTrue(cellFeedHelper.insertFeedGroup(group), ResEnum.BAD_GATEWAY);
        });

        return true;
    }

    @Override
    public List<FeedItem> pageFeedByGroup(Long groupId, PageVo pageVo) {
        pageVo = checkPage(pageVo);
        List<FeedByGroup> feedByGroups = cellFeedHelper.pageFeedByGroup(groupId, pageVo.getPageNo(), pageVo.getPageSize());
        if (CollectionUtils.isEmpty(feedByGroups)) return Collections.emptyList();
        List<Long> feedItemIds = feedByGroups.stream().map(FeedByGroup::getFeedItemId).collect(Collectors.toList());
        return cellFeedHelper.listFeedByIds(feedItemIds);
    }

    @Override
    public List<FeedItem> pageFeedByGroups(List<Long> groupIds, PageVo pageVo) {
        pageVo = checkPage(pageVo);
        List<FeedByGroup> feedByGroups = cellFeedHelper.pageFeedByGroups(groupIds, pageVo.getPageNo(), pageVo.getPageSize());
        if (CollectionUtils.isEmpty(feedByGroups)) return Collections.emptyList();
        List<Long> feedItemIds = feedByGroups.stream().map(FeedByGroup::getFeedItemId).collect(Collectors.toList());
        return cellFeedHelper.listFeedByIds(feedItemIds);
    }

    @Override
    public List<FeedItem> pageFeedByUser(Long userId, PageVo pageVo) {
        pageVo = checkPage(pageVo);
        return cellFeedHelper.pageFeedByUser(userId, FeedItemType.PUBLISHED, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public FeedItem getFeedById(Long feedItemId) {
        return cellFeedHelper.getFeedById(feedItemId);
    }

    @Override
    public FeedTopic getTopicById(Integer topicId) {
        return cellFeedHelper.getTopicById(topicId);
    }

    @Override
    public boolean deleteFeed(Long feedItemId, Long userId) {
        return false;
    }

    @Override
    public Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedItemIds) {
        return cellFeedHelper.mapFeedImages(feedItemIds);
    }

    private void checkFeedItem(FeedItemBo feedItemBo) {
        MAssert.notNull(feedItemBo, ResEnum.INVALID_PARAM);
        FeedItem feedItem = feedItemBo.getFeedItem();
        MAssert.notNull(feedItem, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(feedItem.getUserId()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(StringUtils.isNotBlank(feedItem.getText()) || CollectionUtils.isNotEmpty(feedItemBo.getImages()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(CollectionUtils.isNotEmpty(feedItemBo.getGroupIds()), ResEnum.INVALID_PARAM);
    }

    private PageVo checkPage(PageVo pageVo) {
        if (pageVo == null) {
            return new PageVo(1, defaultPageSize);
        }
        if (!NumberUtil.gtZero(pageVo.getPageNo())) {
            pageVo.setPageNo(1);
        }
        if (!NumberUtil.gtZero(pageVo.getPageSize())) {
            pageVo.setPageSize(defaultPageSize);
        }
        return pageVo;
    }
}
