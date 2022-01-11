package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedCommentsVo {
    /**
     * 评论列表
     */
    private List<FeedCommentVo> comments;

    /**
     * 评论总量
     */
    private Integer count;

    private Integer pageNo;
}
