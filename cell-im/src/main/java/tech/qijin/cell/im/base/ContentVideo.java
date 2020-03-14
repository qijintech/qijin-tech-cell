package tech.qijin.cell.im.base;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class ContentVideo extends AbstractContent {
    // 视频地址
    @NotBlank(message = "视频地址不能为空")
    private String url;

    // 视频时长
    @NotNull(message = "视频时长不能为空")
    private Integer duration;
}
