package tech.qijin.cell.feed.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedComment;

@Data
@Builder
public class CommentReplyBo {
    // 回复的啥
    private FeedComment comment;
    // 给谁回复的
    private FeedComment toComment;
    private boolean hasLiked;
}
