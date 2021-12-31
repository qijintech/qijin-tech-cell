package tech.qijin.cell.relation.base;

import com.google.common.collect.Lists;
import tech.qijin.util4j.lang.constant.EnumStatus;

import java.util.List;

/**
 * relation 状态
 */
public enum RelationStatus implements EnumStatus<RelationStatus, String> {
    NORMAL("正常") {
        @Override
        public List<RelationStatus> next() {
            return Lists.newArrayList(DELETED);
        }
    },
    DELETED("删除") {
        @Override
        public List<RelationStatus> next() {
            return Lists.newArrayList(NORMAL);
        }
    };

    RelationStatus(String description) {
        this.description = description;
    }

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
