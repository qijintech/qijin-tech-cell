package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 账户类型
 */
public enum AccountKind implements EnumValue<String> {
    CASH("现金") {
        @Override
        public Long factor() {
            return 100L;
        }
    },
    PEACH_BLOSSOM("桃花"){
        @Override
        public Long factor() {
            return 1L;
        }
    },
    ;


    AccountKind(String description) {
        this.description = description;
    }


    private String description;

    public abstract Long factor();

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return this.description;
    }
}
