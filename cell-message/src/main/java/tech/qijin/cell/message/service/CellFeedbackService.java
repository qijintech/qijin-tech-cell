package tech.qijin.cell.message.service;

import java.util.List;

/**
 * feedback service
 */
public interface CellFeedbackService {
    /**
     * 用户反馈
     *
     * @param userId
     * @param text
     * @param images
     * @return
     */
    boolean sendFeedback(Long userId, String text, List<String> images);
}
