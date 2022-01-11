package tech.qijin.cell.feed.service.bo;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CommentRepliesBo {
    @Builder.Default
    private List<CommentReplyBo> commentReplies = Lists.newArrayList();
    private Integer replyCount;
}
