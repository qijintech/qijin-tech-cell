package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class FeedTopicsVo {
    private List<FeedTopicVo> topics;
}
