package tech.qijin.cell.feed.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum FeedItemType implements EnumValue<String> {
    PUBLISHED("已发布"),
    DRAFTED("草稿"),
    DELETED("删除"),
    ;

    private String description;

    FeedItemType(String description) {
        this.description = description;
    }

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return null;
    }
}
