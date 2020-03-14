package tech.qijin.cell.im.base;

import lombok.Data;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Data
public class ContentSticker extends AbstractContent {
    // 表情名称
    private String name;

    // 表情地址
    private String url;
}
