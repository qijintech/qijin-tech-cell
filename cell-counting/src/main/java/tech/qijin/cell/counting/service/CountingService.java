package tech.qijin.cell.counting.service;

import tech.qijin.util4j.lang.event.EventBase;

public interface CountingService {

    /**
     * 响应 event
     *
     * @param event
     * @return
     */
    void onEvent(EventBase event);

    /**
     * 注册计数
     *
     * @param userId
     * @param countingCode
     */
    void register(Long userId, String countingCode);

    /**
     * 重置计数
     *
     * @param userId
     * @param countingCode
     */
    void reset(Long userId, String countingCode);
}
