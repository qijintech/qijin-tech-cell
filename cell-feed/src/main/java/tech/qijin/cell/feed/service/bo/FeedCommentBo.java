package tech.qijin.cell.feed.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedComment;

@Data
@Builder
public class FeedCommentBo {
    private FeedComment comment;
    private Boolean hasLiked;
    private Integer likeCount;
//    private List<FeedComment> commentReplies;
//    private Integer replyCount;
}
