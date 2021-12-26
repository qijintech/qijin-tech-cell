package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 掉落的类型
 */
public enum DropsItemKind implements EnumValue<String> {
    ITEM("道具"),
    // 支持嵌套掉落
    DROPS("掉落"),
    ;


    DropsItemKind(String description) {
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
