package tech.qijin.cell.feed.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.cell.counting.service.CellCountingService;
import tech.qijin.cell.feed.base.CellFeedBo;
import tech.qijin.cell.feed.base.FeedType;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedByGroup;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.event.FeedEvent;
import tech.qijin.cell.feed.event.FeedEventType;
import tech.qijin.cell.feed.helper.CellFeedHelper;
import tech.qijin.cell.feed.service.CellFeedService;
import tech.qijin.cell.feed.service.CellLikeService;
import tech.qijin.cell.feed.service.CommonService;
import tech.qijin.cell.feed.service.bo.FeedBo;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.event.FeedPublishEvent;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellFeedServiceImpl extends CommonService implements CellFeedService {
    @Autowired
    private CellFeedHelper cellFeedHelper;
    @Autowired
    private CellCountingService cellCountingService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createFeedByGroup(CellFeedBo cellFeedBo) {
        checkFeedItem(cellFeedBo);
        Feed feed = cellFeedBo.getFeed();
        List<Long> groupIds = cellFeedBo.getGroupIds();
        feed.setType(FeedType.PUBLISHED);

        // 插入feed
        MAssert.isTrue(cellFeedHelper.insertFeed(feed), ResEnum.BAD_GATEWAY);
        // 插入图片
        if (CollectionUtils.isNotEmpty(cellFeedBo.getImages())) {
            List<FeedImage> images = cellFeedBo.getImages().stream().map(image -> {
                FeedImage feedImage = new FeedImage();
                feedImage.setUrl(image);
                feedImage.setFeedId(feed.getId());
                return feedImage;
            }).collect(Collectors.toList());
            MAssert.isTrue(cellFeedHelper.insertFeedImages(images), ResEnum.BAD_GATEWAY);
        }
        // 按group插入
        groupIds.stream().forEach(groupId -> {
            FeedByGroup group = new FeedByGroup();
            group.setFeedId(feed.getId());
            group.setGroupId(groupId);
            group.setUserId(feed.getUserId());
            MAssert.isTrue(cellFeedHelper.insertFeedGroup(group), ResEnum.BAD_GATEWAY);
        });

        eventPublisher.publishEvent(FeedEvent.builder()
                .type(FeedEventType.CREATE)
                .feedId(feed.getId())
                .build()
                .extra("groupIds", groupIds));
        cellCountingService.onEvent(FeedPublishEvent.builder()
                .userId(cellFeedBo.getFeed().getUserId())
                .build());

        return true;
    }

    @Override
    public List<Feed> pageFeedByGroup(Long groupId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        List<FeedByGroup> feedByGroups = cellFeedHelper.pageFeedByGroup(groupId, pageVo.getPageNo(), pageVo.getPageSize());
        if (CollectionUtils.isEmpty(feedByGroups)) return Collections.emptyList();
        List<Long> feedIds = feedByGroups.stream().map(FeedByGroup::getFeedId).collect(Collectors.toList());
        return cellFeedHelper.listFeedByIds(feedIds);
    }

    @Override
    public List<Feed> pageFeedByGroups(List<Long> groupIds, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        List<FeedByGroup> feedByGroups = cellFeedHelper.pageFeedByGroups(groupIds, pageVo.getPageNo(), pageVo.getPageSize());
        if (CollectionUtils.isEmpty(feedByGroups)) return Collections.emptyList();
        Set<Long> feedIds = feedByGroups.stream().map(FeedByGroup::getFeedId).collect(Collectors.toSet());
        List<Feed> feeds = cellFeedHelper.listFeedByIds(Lists.newArrayList(feedIds));
        feeds.sort(Comparator.comparing(Feed::getId).reversed());
        return feeds;
    }

    @Override
    public List<Feed> pageFeedByUser(Long userId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        return cellFeedHelper.pageFeedByUser(userId, FeedType.PUBLISHED, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public Feed getFeedById(Long feedId) {
        return cellFeedHelper.getFeedById(feedId);
    }

    @Override
    public FeedTopic getTopicById(Integer topicId) {
        return cellFeedHelper.getTopicById(topicId);
    }

    @Override
    public boolean deleteFeed(Long feedId, Long userId) {
        Feed feed = cellFeedHelper.getFeedById(feedId);
        MAssert.isTrue(feed != null, ResEnum.FORBIDDEN);
        MAssert.isTrue(feed.getUserId().equals(userId), ResEnum.FORBIDDEN);
        if (!FeedType.PUBLISHED.equals(feed.getType())) {
            log.warn("deleteFeed feed not published, feedId={}", feedId);
            return true;
        }
        Feed record = new Feed();
        record.setId(feed.getId());
        record.setType(FeedType.DELETED);
        if (cellFeedHelper.updateFeed(record)) {
            eventPublisher.publishEvent(FeedEvent.builder()
                    .type(FeedEventType.DELETE)
                    .feedId(feedId)
                    .build());
            return true;
        }
        return false;
    }

    @Override
    public List<FeedImage> getFeedImages(Long feedId) {
        return cellFeedHelper.mapFeedImages(Lists.newArrayList(feedId)).get(feedId);
    }

    @Override
    public Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedIds) {
        return cellFeedHelper.mapFeedImages(feedIds);
    }

    @Override
    public List<FeedTopic> pageFeedTopic(PageVo pageVo) {
        pageVo = checkPage(pageVo, 100);
        return cellFeedHelper.pageFeedTopic(pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public List<FeedBo> withFeedImage(List<Feed> feeds) {
        if (CollectionUtils.isEmpty(feeds)) return Collections.emptyList();
        List<Long> feedIds = feeds.stream()
                .map(Feed::getId)
                .collect(Collectors.toList());
        Map<Long, List<FeedImage>> feedImagesMap = mapFeedImages(feedIds);
        return feeds.stream().map(feed ->
                FeedBo.builder()
                        .feed(feed)
                        .images(feedImagesMap.get(feed.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public boolean copyFeedIntoGroup(Long userId, Long groupId) {
        Integer pageNo = 1;
        Integer pageSize = 50;
        Integer total = 0;
        Date timeLimit = DateUtil.plusDays(DateUtil.now(), -30);
        while (true) {
            List<Feed> feeds = cellFeedHelper.pageFeedByUserAndDate(userId, FeedType.PUBLISHED, new PageVo(pageNo, pageSize), timeLimit);
            if (CollectionUtils.isEmpty(feeds)) break;
            pageNo++;
            List<FeedByGroup> feedByGroups = feeds.stream().map(feed -> {
                FeedByGroup fg = new FeedByGroup();
                fg.setUserId(feed.getUserId());
                fg.setFeedId(feed.getId());
                fg.setGroupId(groupId);
                fg.setCreateTime(feed.getCreateTime());
                fg.setUpdateTime(feed.getUpdateTime());
                return fg;
            }).collect(Collectors.toList());
            total += feedByGroups.size();
            cellFeedHelper.batchInsertFeedGroup(feedByGroups);
        }
        log.info("copyFeedIntoGroup userId={}, groupId={}, total={}", userId, groupId, total);
        return true;
    }

    @Override
    public long removeFeedFromGroup(Long userId, Long groupId) {
        return cellFeedHelper.removeFeedGroup(userId, groupId);
    }

    private void checkFeedItem(CellFeedBo cellFeedBo) {
        MAssert.notNull(cellFeedBo, ResEnum.INVALID_PARAM);
        Feed feed = cellFeedBo.getFeed();
        MAssert.notNull(feed, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(feed.getUserId()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(StringUtils.isNotBlank(feed.getText()) || CollectionUtils.isNotEmpty(cellFeedBo.getImages()), ResEnum.INVALID_PARAM);
//        MAssert.isTrue(CollectionUtils.isNotEmpty(cellFeedBo.getGroupIds()), ResEnum.INVALID_PARAM);
    }

}
