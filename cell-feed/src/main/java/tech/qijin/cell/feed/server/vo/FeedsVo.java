package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedsVo {
    private List<FeedVo> feeds;
    private Integer pageNo;
}
