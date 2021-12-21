package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 流水来源
 */
public enum StatementSrc implements EnumValue<String> {
    TASK("任务"),
    DEPOSIT("充值"),
    SHARE("分享"),
    INVITE("拉新"),
    ;


    StatementSrc(String description) {
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
