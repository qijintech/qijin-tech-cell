package tech.qijin.cell.im.base;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
public class MessageSendVo {
    // 会话id(会话id作为前端传参，我认为并不是很好的设计)
    private String conversationId;

    private Long uid;

    @NotNull(message = "接收者id不能为空")
    private Long toUid;

    @NotNull(message = "消息类型不能为空")
    private MsgType msgType;

    @NotNull(message = "消息内容不能为空")
    private AbstractContent content;

    // 扩展字段
    private String ext;
}
