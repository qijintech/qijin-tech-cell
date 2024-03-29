package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.base.FeedType;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedByGroup;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface CellFeedHelper {

    boolean insertFeed(Feed feed);

    boolean insertFeedGroup(FeedByGroup feedGroup);

    boolean batchInsertFeedGroup(List<FeedByGroup> feedGroups);

    boolean updateFeed(Feed record);

    long removeFeedGroup(Long userId, Long groupId);

    boolean insertFeedImages(List<FeedImage> images);

    Feed getFeedById(Long feedId);

    FeedTopic getTopicById(Integer topicId);

    List<Feed> listFeedByIds(List<Long> feedIds);

    Map<Long, Feed> mapFeedByIds(List<Long> feedIds);

    List<FeedByGroup> pageFeedByGroup(Long groupId, Integer pageNo, Integer pageSize);

    List<FeedByGroup> pageFeedByGroups(List<Long> groupIds, Integer pageNo, Integer pageSize);

    List<Feed> pageFeedByUser(Long userId, FeedType type, Integer pageNo, Integer pageSize);

    List<Feed> pageFeedByUserAndDate(Long userId, FeedType type, PageVo pageVo, Date timeLimit);

    List<FeedImage> listFeedImages(List<Long> feedIds);

    Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedIds);

    /**
     * ============= topic =============
     */
    List<FeedTopic> pageFeedTopic(Integer pageNo, Integer pageSize);
}
