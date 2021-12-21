package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 道具类型
 */
public enum ItemKind implements EnumValue<String> {
    PEACH_BLOSSOM("桃花"),
    ;


    ItemKind(String description) {
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
