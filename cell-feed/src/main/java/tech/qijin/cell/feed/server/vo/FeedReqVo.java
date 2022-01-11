package tech.qijin.cell.feed.server.vo;

import lombok.Data;
import tech.qijin.util4j.lang.vo.PageVo;

@Data
public class FeedReqVo extends PageVo {
    private Long feedId;
}
