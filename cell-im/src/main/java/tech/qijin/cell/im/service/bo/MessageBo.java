package tech.qijin.cell.im.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.im.db.model.ImMessageContent;
import tech.qijin.cell.im.db.model.ImMessageInfo;

/**
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
public class MessageBo {
    private Long msgId;
    private ImMessageInfo messageInfo;
    private ImMessageContent messageContent;
}
