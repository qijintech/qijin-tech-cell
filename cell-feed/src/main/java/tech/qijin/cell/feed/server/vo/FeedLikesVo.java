package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedLikesVo {
    private List<FeedLikeVo> likes;
    private Integer count;
}
