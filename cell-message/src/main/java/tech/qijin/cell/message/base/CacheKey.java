package tech.qijin.cell.message.base;

import tech.qijin.util4j.lang.template.ICacheKey;

public enum CacheKey implements ICacheKey {
    INSTANCE;

    private static final String MODULE = "message";

    @Override
    public String module() {
        return MODULE;
    }


    /**
     * 未读数计数
     *
     * @param userId
     * @return
     */
    public String unreadCountKey(Long userId) {
        return key("unread", "count", String.valueOf(userId));
    }
}