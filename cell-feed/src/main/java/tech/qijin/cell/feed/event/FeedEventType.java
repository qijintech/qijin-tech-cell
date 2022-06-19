package tech.qijin.cell.feed.event;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * feed event type
 */
public enum FeedEventType implements EnumValue<String> {
    CREATE("添加"),
    UPDATE("更新"),
    DELETE("删除"),
    ;


    FeedEventType(String description) {
        this.description = description;
    }

    private String description;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return description;
    }
}
