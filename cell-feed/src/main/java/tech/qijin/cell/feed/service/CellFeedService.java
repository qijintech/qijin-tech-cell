package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.base.FeedBo;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedImage;
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
     * @param feedBo
     * @return
     */
    boolean createFeedByGroup(FeedBo feedBo);

    /**
     * 分页返回feed，根据group区分
     * @param groupId
     * @param pageVo
     * @return
     */
    List<Feed> pageFeedByGroup(Long groupId, PageVo pageVo);
    List<Feed> pageFeedByGroups(List<Long> groupIds, PageVo pageVo);

    /**
     * 分页获取单个用户发布的feed
     * @param userId
     * @param pageVo
     * @return
     */
    List<Feed> pageFeedByUser(Long userId, PageVo pageVo);

    Feed getFeedById(Long feedId);

    FeedTopic getTopicById(Integer topicId);

    /**
     * 删除指定feed
     * @param feedId
     * @param userId
     * @return
     */
    boolean deleteFeed(Long feedId, Long userId);

    Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedIds);

}
