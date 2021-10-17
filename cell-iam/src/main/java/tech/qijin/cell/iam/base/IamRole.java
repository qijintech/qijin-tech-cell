package tech.qijin.cell.iam.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum IamRole implements EnumValue<String> {
    CREATOR("造物主"),

    // SOCIAL
    GROUP_CREATOR("俱乐部创建者"),
    GROUP_ADMIN("俱乐部管理员"),
    ACTIVITY_ADMIN("活动管理员"),
    ;


    IamRole(String description) {
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
