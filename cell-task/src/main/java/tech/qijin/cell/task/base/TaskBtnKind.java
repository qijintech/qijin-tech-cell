package tech.qijin.cell.task.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 任务类型
 */
public enum TaskBtnKind implements EnumValue<String> {
    ONCE("永久任务/成就任务"),
    DAILY("每日模式"),
    WEEKLY("每周模式"),
    MONTHLY("每月模式"),
    YEARLY("每年模式"),;

    TaskBtnKind(String description) {
        this.description = description;
    }

    private String description;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return this.description;
    }
}
