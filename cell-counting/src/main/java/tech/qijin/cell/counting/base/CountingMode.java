package tech.qijin.cell.counting.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 计数模式
 */
public enum CountingMode implements EnumValue<String> {
    ACCUMULATE("累计模式"),
    DAILY("每日模式"),
    WEEKLY("每周模式"),
    MONTHLY("每月模式"),
    YEARLY("每年模式"),
    ;

    private String description;

    CountingMode(String description) {
        this.description = description;
    }

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return this.description;
    }
}
