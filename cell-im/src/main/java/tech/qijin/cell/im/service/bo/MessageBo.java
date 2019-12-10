package tech.qijin.cell.im.service.bo;

import lombok.Data;
import tech.qijin.cell.im.base.MsgType;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class MessageBo {
    private Long conversationId;
    private Long toUid;
    private MsgType msgType;
    private Content content;
}
