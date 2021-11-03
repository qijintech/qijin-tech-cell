package tech.qijin.cell.feed.base;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedItem;

import java.util.List;

@Data
@Builder
public class FeedItemBo {
    private FeedItem feedItem;
    private List<String> images;
    private List<Long> groupIds;
}
