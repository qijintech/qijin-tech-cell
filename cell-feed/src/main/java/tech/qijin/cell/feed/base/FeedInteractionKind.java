package tech.qijin.cell.feed.base;

import tech.qijin.util4j.lang.constant.EnumValue;

public enum FeedInteractionKind implements EnumValue<String> {
    COMMENT_FEED("评论 feed，记录里有feedId"){
        @Override
        public boolean isLike() {
            return false;
        }
    },
    LIKE_FEED("喜欢 feed，记录里有feedId"){
        @Override
        public boolean isLike() {
            return true;
        }
    },
    REPLY_COMMENT("回复评论，需要二次查询feedId"){
        @Override
        public boolean isLike() {
            return false;
        }
    },
    LIKE_COMMENT("喜欢评论，需要二次查询feedId"){
        @Override
        public boolean isLike() {
            return true;
        }
    },
    REPLY_REPLY("对回复的回复，需要二次查询feedId"){
        @Override
        public boolean isLike() {
            return false;
        }
    }
    ;

    private String description;

    public abstract boolean isLike();

    FeedInteractionKind(String description) {
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
