package tech.qijin.cell.iam.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum IamAuth implements EnumValue<String> {

    // SOCIAL
    ACTIVITY_CREATE("创建/编辑活动权限"),
    ACTIVITY_INFO("读取活动权限"),
    ;


    IamAuth(String description) {
        this.description = description;
    }

    private String description;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return description;
    }
}
