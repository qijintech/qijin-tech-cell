package tech.qijin.cell.im.base;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class ContentImg extends AbstractContent {
    // 图片地址
    @NotBlank(message = "图片地址不能为空")
    private String url;
}
