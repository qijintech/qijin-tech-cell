package tech.qijin.cell.message.base;

import com.google.common.collect.Lists;
import tech.qijin.util4j.lang.constant.EnumStatus;

import java.util.Collections;
import java.util.List;

/**
 * 奖励状态
 */
public enum MessageDropsStatus implements EnumStatus<MessageDropsStatus, String> {
    UNCLAIMED("待领取"){
        @Override
        public List<MessageDropsStatus> next() {
            return Lists.newArrayList(CLAIMED);
        }
    },
    CLAIMED("已领取"){
        @Override
        public List<MessageDropsStatus> next() {
            return Collections.emptyList();
        }
    },
    ;


    MessageDropsStatus(String description) {
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
