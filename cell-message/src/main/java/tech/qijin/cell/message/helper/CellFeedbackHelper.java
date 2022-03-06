package tech.qijin.cell.message.helper;

import tech.qijin.cell.message.db.model.Feedback;

import java.util.List;

public interface CellFeedbackHelper {
    /**
     * 创建 feedback
     *
     * @param feedback
     * @param images
     * @return
     */
    boolean addFeedback(Feedback feedback, List<String> images);
}
