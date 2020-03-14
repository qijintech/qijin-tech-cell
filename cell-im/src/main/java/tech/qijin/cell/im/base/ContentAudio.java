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
public class ContentAudio extends AbstractContent {
    // 音频地址
    @NotBlank(message = "音频地址不能为空")
    private String url;

    // 音频时长
    @NotNull(message = "音频时长不能为空")
    private Integer duration;
}
