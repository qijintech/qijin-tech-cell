package tech.qijin.cell.account.base;

import tech.qijin.cell.account.db.model.Account;
import tech.qijin.util4j.lang.constant.EnumValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    PEACH_BLOSSOM("桃花") {
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

    private static Map<String, AccountKind> accountKindMap = Arrays.stream(AccountKind.values())
            .collect(Collectors.toMap(kind -> kind.name(), Function.identity()));

    public static AccountKind from(String kind) {
        return accountKindMap.get(kind);
    }

}
