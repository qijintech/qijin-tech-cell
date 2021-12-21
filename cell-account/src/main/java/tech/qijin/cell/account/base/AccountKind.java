package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 账户类型
 */
public enum AccountKind implements EnumValue<String> {
    ITEM("道具"),
    ;


    AccountKind(String description) {
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
