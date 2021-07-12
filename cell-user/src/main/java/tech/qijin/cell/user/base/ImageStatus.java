package tech.qijin.cell.user.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum ImageStatus implements EnumValue<String> {
    SHOW("正常"),
    HIDE("不显示"),

    ;

    private String description;

    ImageStatus(String description) {
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
