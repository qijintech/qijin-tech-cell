package tech.qijin.cell.message.base;

import com.google.common.collect.Lists;
import tech.qijin.util4j.lang.constant.EnumStatus;

import java.util.Collections;
import java.util.List;

/**
 * 消息状态
 */
public enum MessageStatus implements EnumStatus<MessageStatus, String> {
    NORMAL("正常消息"){
        @Override
        public List<MessageStatus> next() {
            return Lists.newArrayList(DELETED);
        }
    },
    DELETED("删除消息"){
        @Override
        public List<MessageStatus> next() {
            return Collections.emptyList();
        }
    },
    ;


    MessageStatus(String description) {
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
