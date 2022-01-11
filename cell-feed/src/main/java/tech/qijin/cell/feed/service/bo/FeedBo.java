package tech.qijin.cell.feed.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedImage;

import java.util.List;

@Data
@Builder
public class FeedBo {
    private Feed feed;
    private List<FeedImage> images;
    private Boolean hasLiked;
}
