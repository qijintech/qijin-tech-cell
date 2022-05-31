package tech.qijin.cell.relation.base;

import tech.qijin.util4j.lang.template.ICacheKey;
import tech.qijin.util4j.utils.DateUtil;

public enum CacheKey implements ICacheKey {
    INSTANCE;

    private static final String MODULE = "relation";

    @Override
    public String module() {
        return MODULE;
    }


    /**
     * 未读数计数
     *
     * @param userId
     * @param kind
     * @return
     */
    public String unreadCountKey(Long userId, RelationKind kind) {
        return key("unread", "count", kind.name(), String.valueOf(userId));
    }

    public String dailyAddCountKey(Long userId, RelationKind kind) {
        return key("daily", "add", "count", kind.name(), String.valueOf(userId), DateUtil.formatStr(DateUtil.now(), DateUtil.YYYYMMDD));
    }

    public String dailyMinusCountKey(Long userId, RelationKind kind) {
        return key("daily", "minus", "count", kind.name(), String.valueOf(userId), DateUtil.formatStr(DateUtil.now(), DateUtil.YYYYMMDD));
    }
}