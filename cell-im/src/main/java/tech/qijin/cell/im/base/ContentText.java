package tech.qijin.cell.im.base;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
@Builder
public class ContentText extends AbstractContent {
    // 文本内容
    @NotBlank(message = "消息内容不能为空")
    private String text;
}
