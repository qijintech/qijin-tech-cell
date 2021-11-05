package tech.qijin.cell.feed.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.cell.feed.base.FeedBo;
import tech.qijin.cell.feed.base.FeedType;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedByGroup;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.helper.CellFeedHelper;
import tech.qijin.cell.feed.service.CellFeedService;
import tech.qijin.cell.feed.service.CommonService;
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
public class CellFeedServiceImpl extends CommonService implements CellFeedService {
    @Autowired
    private CellFeedHelper cellFeedHelper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean createFeedByGroup(FeedBo feedBo) {
        checkFeedItem(feedBo);
        Feed feed = feedBo.getFeed();
        List<Long> groupIds = feedBo.getGroupIds();
        feed.setType(FeedType.PUBLISHED);

        // 插入feed
        MAssert.isTrue(cellFeedHelper.insertFeed(feed), ResEnum.BAD_GATEWAY);
        // 插入图片
        if (CollectionUtils.isNotEmpty(feedBo.getImages())) {
            List<FeedImage> images = feedBo.getImages().stream().map(image -> {
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
            MAssert.isTrue(cellFeedHelper.insertFeedGroup(group), ResEnum.BAD_GATEWAY);
        });

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
        List<Long> feedIds = feedByGroups.stream().map(FeedByGroup::getFeedId).collect(Collectors.toList());
        return cellFeedHelper.listFeedByIds(feedIds);
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
        return false;
    }

    @Override
    public Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedIds) {
        return cellFeedHelper.mapFeedImages(feedIds);
    }

    private void checkFeedItem(FeedBo feedBo) {
        MAssert.notNull(feedBo, ResEnum.INVALID_PARAM);
        Feed feed = feedBo.getFeed();
        MAssert.notNull(feed, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(feed.getUserId()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(StringUtils.isNotBlank(feed.getText()) || CollectionUtils.isNotEmpty(feedBo.getImages()), ResEnum.INVALID_PARAM);
        MAssert.isTrue(CollectionUtils.isNotEmpty(feedBo.getGroupIds()), ResEnum.INVALID_PARAM);
    }

}