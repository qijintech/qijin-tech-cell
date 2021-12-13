package tech.qijin.cell.counting.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 计数模式
 */
public enum OnTargetMode implements EnumValue<String> {
    END("达到目标后终止计数"),
    CYCLE("达到目标后循环计数"), // 循环计数会多次触发
    ;

    private String description;

    OnTargetMode(String description) {
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
