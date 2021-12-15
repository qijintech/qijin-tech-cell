package tech.qijin.cell.task.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 奖励类型
 */
public enum RewardType implements EnumValue<String> {
    DROPS("掉落奖励"),
    ;

    RewardType(String description) {
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
