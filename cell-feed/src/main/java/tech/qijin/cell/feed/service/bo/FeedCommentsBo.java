package tech.qijin.cell.feed.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedComment;

import java.util.List;

@Data
@Builder
public class FeedCommentsBo {
    private Integer count;
    private List<FeedComment> comments;
}
