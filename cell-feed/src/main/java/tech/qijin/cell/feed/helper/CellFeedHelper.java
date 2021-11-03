package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.base.FeedItemType;
import tech.qijin.cell.feed.db.model.FeedByGroup;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedItem;
import tech.qijin.cell.feed.db.model.FeedTopic;

import java.util.List;
import java.util.Map;

public interface CellFeedHelper {

    boolean insertFeed(FeedItem feedItem);

    boolean insertFeedGroup(FeedByGroup feedGroup);

    boolean insertFeedImages(List<FeedImage> images);

    FeedItem getFeedById(Long feedItemId);

    FeedTopic getTopicById(Integer topicId);

    List<FeedItem> listFeedByIds(List<Long> feedItemIds);

    List<FeedByGroup> pageFeedByGroup(Long groupId, Integer pageNo, Integer pageSize);

    List<FeedByGroup> pageFeedByGroups(List<Long> groupIds, Integer pageNo, Integer pageSize);

    List<FeedItem> pageFeedByUser(Long userId, FeedItemType type, Integer pageNo, Integer pageSize);

    List<FeedImage> listFeedImages(List<Long> feedItemIds);
    Map<Long, List<FeedImage>> mapFeedImages(List<Long> feedItemIds);
}
