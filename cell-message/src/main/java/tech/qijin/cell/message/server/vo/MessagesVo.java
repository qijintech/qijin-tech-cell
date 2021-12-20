package tech.qijin.cell.message.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessagesVo {
    private List<MessageVo> message;
    private Boolean hasMore;
}
