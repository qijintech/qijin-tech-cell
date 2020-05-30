package tech.qijin.cell.im.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2019-11-15
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum MsgType implements EnumValue<String> {
    TEXT("文本消息"),
    IMG("图片消息"),
    AUDIO("音频消息"),
    VIDEO("视频消息"),
    LOCATION("位置消息"),
    STICKER("表情消息");

    private String desc;

    MsgType(String desc) {
        this.desc = desc;
    }

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return this.desc;
    }
}
