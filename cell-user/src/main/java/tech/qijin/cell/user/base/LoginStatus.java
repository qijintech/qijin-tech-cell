package tech.qijin.cell.user.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2019/1/16
 * 开始做眼保健操：←_← ↑_↑ →_→ ↓_↓
 **/
public enum LoginStatus implements EnumValue<String> {
    NULL("无效"),
    NEW("新增"),
    OLD("已有");

    LoginStatus(String desc) {
        this.desc = desc;
    }

    private String desc;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return desc;
    }
}
