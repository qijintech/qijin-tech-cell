package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.base.FeedItemBo;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedItem;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;
import java.util.Map;

/**
 * feed 相关方法
 */
public interface CellFeedService {
    /**
     * 发布feed
     *
     * @param feedItemBo
     * @return
     */
    boolean createFeedByGroup(FeedItemBo feedItemBo);

    /**
     * 分页返回feed，根据group区分
     * @param groupId
     * @param pageVo
     * @return
     */
    List<FeedItem> pageFeedByGroup(Long groupId, PageVo pageVo);
    List<FeedItem> pageFeedByGroups(List<Long> groupIds, PageVo pageVo);

    /**
     * 分页获取单个用户发布的feed
     * @param userId
     * @param pageVo
     * @return
     */
    List<FeedItem> pageFeedByUser(Long userId, PageVo pageVo);

    FeedItem getFeedById(Long feedItemId);

    FeedTopic getTopicById(Integer topicId);

    /**
     * 删除指定feed
     * @param feedItemId
     * @param userId
     * @return
     */
    boolean deleteFeed(Long feedItemId, Long userId);

    Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedItemIds);
}
