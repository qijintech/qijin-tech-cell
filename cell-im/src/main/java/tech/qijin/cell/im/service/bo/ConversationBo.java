package tech.qijin.cell.im.service.bo;

import lombok.Data;
import tech.qijin.cell.im.db.model.ImConversation;

/**
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class ConversationBo {
    private ImConversation conversation;
    // 最后一条消息
    private MessageBo messageBo;
}
