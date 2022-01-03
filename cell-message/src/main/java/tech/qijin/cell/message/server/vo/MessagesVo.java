package tech.qijin.cell.message.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessagesVo {
    private List<MessageVo> message;
    private Boolean hasMore;

    // 消息总数
    private Long count;
    // 消息未读数
    private Long unreadCount;
}
