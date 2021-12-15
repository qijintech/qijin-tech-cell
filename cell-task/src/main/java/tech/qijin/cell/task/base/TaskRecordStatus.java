package tech.qijin.cell.task.base;

import com.google.common.collect.Lists;
import tech.qijin.util4j.lang.constant.EnumStatus;

import java.util.Collections;
import java.util.List;

public enum TaskRecordStatus implements EnumStatus<TaskRecordStatus, String> {
    PROCESSING("进行中"){
        @Override
        public List<TaskRecordStatus> next() {
            return Lists.newArrayList(FINISH_UNCLAIMED);
        }
    },
    FINISH_UNCLAIMED("完成未领奖"){
        @Override
        public List<TaskRecordStatus> next() {
            return Lists.newArrayList(FINISH_CLAIMED);
        }
    },
    FINISH_CLAIMED("完成已领奖"){
        @Override
        public List<TaskRecordStatus> next() {
            return Collections.emptyList();
        }
    },
    ;


    TaskRecordStatus(String description) {
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
