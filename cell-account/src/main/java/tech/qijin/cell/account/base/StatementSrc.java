package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * 流水来源
 */
public enum StatementSrc implements EnumValue<String> {
    TASK("任务") {
        @Override
        public boolean isNegative() {
            return false;
        }
    },
    DEPOSIT("充值") {
        @Override
        public boolean isNegative() {
            return false;
        }
    },
    SHARE("分享") {
        @Override
        public boolean isNegative() {
            return false;
        }
    },
    INVITE("拉新") {
        @Override
        public boolean isNegative() {
            return false;
        }
    },
    ;


    StatementSrc(String description) {
        this.description = description;
    }

    // 是否应该是负数
    public abstract boolean isNegative();


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
