package tech.qijin.cell.im.base;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
public class MessageSendBo {
    @NotNull(message = "发送者uid不能为空")
    private Long uid;

    @NotNull(message = "接收者uid不能为空")
    private Long toUid;

    @NotNull(message = "消息类型不能为空")
    private MsgType msgType;

    @NotNull(message = "消息内容不能为空")
    private AbstractContent content;

    // 扩展字段
    private Map<String, Object> ext;

    // 是否静默发送。如果为true，则接收方收不到消息
    private boolean silent;
}
