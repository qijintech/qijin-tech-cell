package tech.qijin.cell.account.base;

import tech.qijin.util4j.lang.template.ICacheKey;

public enum CacheKey implements ICacheKey {
    INSTANCE;

    private static final String MODULE = "account";

    @Override
    public String module() {
        return MODULE;
    }


    /**
     * 小红点key
     *
     * @param userId
     * @param kind
     * @return
     */
    public String redPointKey(Long userId, AccountKind kind) {
        return key("red", "point", kind.name(), String.valueOf(userId));
    }
}