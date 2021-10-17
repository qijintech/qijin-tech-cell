package tech.qijin.cell.user.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum MaritalStatus implements EnumValue<String> {
    SINGLE("单身"),
    DIVORCED("离异"),
    WIDOWED("丧偶"),
    ;


    MaritalStatus(String description) {
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
