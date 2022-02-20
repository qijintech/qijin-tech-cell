package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.base.CellFeedBo;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.service.bo.FeedBo;
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
     * @param cellFeedBo
     * @return
     */
    boolean createFeedByGroup(CellFeedBo cellFeedBo);

    /**
     * 分页返回feed，根据group区分
     *
     * @param groupId
     * @param pageVo
     * @return
     */
    List<Feed> pageFeedByGroup(Long groupId, PageVo pageVo);

    List<Feed> pageFeedByGroups(List<Long> groupIds, PageVo pageVo);

    /**
     * 分页获取单个用户发布的feed
     *
     * @param userId
     * @param pageVo
     * @return
     */
    List<Feed> pageFeedByUser(Long userId, PageVo pageVo);

    Feed getFeedById(Long feedId);

    FeedTopic getTopicById(Integer topicId);

    /**
     * 删除指定feed
     *
     * @param feedId
     * @param userId
     * @return
     */
    boolean deleteFeed(Long feedId, Long userId);

    List<FeedImage> getFeedImages(Long feedId);

    Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedIds);

    /**
     * ============= topic =============
     */
    List<FeedTopic> pageFeedTopic(PageVo pageVo);

    List<FeedBo> withFeedImage(List<Feed> feeds);

    /**
     * 用户进入群组后，将用户之前发的feed添加到新群组中
     *
     * @param userId
     * @param groupId
     * @return
     */
    boolean copyFeedIntoGroup(Long userId, Long groupId);

    /**
     * 用户退群后，将用户之前发的feed从群组中移除
     *
     * @param userId
     * @param groupId
     * @return
     */
    long removeFeedFromGroup(Long userId, Long groupId);
}
