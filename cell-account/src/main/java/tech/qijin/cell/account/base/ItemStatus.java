package tech.qijin.cell.account.base;

import com.google.common.collect.Lists;
import tech.qijin.util4j.lang.constant.EnumStatus;

import java.util.Collections;
import java.util.List;

/**
 * 道具状态
 */
public enum ItemStatus implements EnumStatus<ItemStatus, String> {
    NORMAL("正常"){
        @Override
        public List<ItemStatus> next() {
            return Lists.newArrayList();
        }
    },

    ;


    ItemStatus(String description) {
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
