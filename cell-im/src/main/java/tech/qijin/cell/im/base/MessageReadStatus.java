package tech.qijin.cell.im.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 会话状态
 */
public enum MessageReadStatus implements EnumValue<Integer> {
    NORMAL(0, "正常"), DELETED(1, "被删除");

    MessageReadStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private int code;
    private String desc;

    @Override
    public Integer value() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
