package tech.qijin.cell.feed.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedInteraction;

@Data
@Builder
public class FeedInteractionBo {
    private FeedInteraction interaction;
    private FeedBo feedBo;
    private FeedComment comment;
}
