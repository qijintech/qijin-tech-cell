package tech.qijin.cell.user.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum UserStatus implements EnumValue<String> {
    NORMAL("正常"),

    ;

    private String description;

    UserStatus(String description) {
        this.description = description;
    }

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return null;
    }
}
