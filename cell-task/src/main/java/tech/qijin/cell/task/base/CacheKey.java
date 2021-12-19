package tech.qijin.cell.task.base;

import tech.qijin.util4j.lang.template.ICacheKey;

public enum CacheKey implements ICacheKey {
    INSTANCE;

    private static final String MODULE = "task";

    @Override
    public String module() {
        return MODULE;
    }


    /**
     * 触发任务冷却窗口
     *
     * @return
     */
    public String taskTriggerWindow(Long userId) {
        return key("task", "trigger", "window", String.valueOf(userId));
    }
}