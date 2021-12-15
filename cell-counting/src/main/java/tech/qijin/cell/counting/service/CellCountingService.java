package tech.qijin.cell.counting.service;

import tech.qijin.cell.counting.db.model.CountingTemplate;
import tech.qijin.util4j.lang.event.EventBase;

public interface CellCountingService {

    /**
     * 响应 event
     *
     * @param event
     * @return
     */
    void onEvent(EventBase event);

    /**
     * 查询对应counting code的当前值
     * @param userId
     * @param countingCode
     * @return
     */
    Long query(Long userId, String countingCode);

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

    void incr(Long userId, EventBase event, CountingTemplate template);
}
