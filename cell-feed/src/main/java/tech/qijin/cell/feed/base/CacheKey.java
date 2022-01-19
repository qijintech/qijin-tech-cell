package tech.qijin.cell.feed.base;

import tech.qijin.util4j.lang.template.ICacheKey;

public enum CacheKey implements ICacheKey {
    INSTANCE;

    private static final String MODULE = "feed";

    @Override
    public String module() {
        return MODULE;
    }


    /**
     * interaction 未读数计数
     *
     * @param userId
     * @return
     */
    public String interactionUnreadCountKey(Long userId) {
        return key("interaction", "unread", "count", String.valueOf(userId));
    }
}