package tech.qijin.cell.relation.base;

import tech.qijin.util4j.lang.constant.EnumValue;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 关系类型
 */
public enum RelationKind implements EnumValue<String> {
    FOLLOW("关注") {
        @Override
        public boolean isBidirectional() {
            return false;
        }
    },
    FOLLOWED("被关注") {
        @Override
        public boolean isBidirectional() {
            return false;
        }
    },
    VISIT("访问") {
        @Override
        public boolean isBidirectional() {
            return false;
        }
    },
    VISITED("被访问") {
        @Override
        public boolean isBidirectional() {
            return false;
        }
    },
    FRIEND("好友") {
        @Override
        public boolean isBidirectional() {
            return true;
        }
    },
    ;

    RelationKind(String description) {
        this.description = description;
    }

    // 是否是双向关系。如果是双向关系，则需要同时添加和同时解除
    public abstract boolean isBidirectional();

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
