package tech.qijin.cell.im.base;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.im.db.model.ImMessage;

/**
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
public class MessageBo {
    private ImMessage imMessage;
}
