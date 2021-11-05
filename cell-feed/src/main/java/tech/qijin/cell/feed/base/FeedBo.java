package tech.qijin.cell.feed.base;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.Feed;

import java.util.List;

@Data
@Builder
public class FeedBo {
    private Feed feed;
    private List<String> images;
    private List<Long> groupIds;
}
