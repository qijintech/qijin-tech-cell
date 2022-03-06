package tech.qijin.cell.message.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.message.db.model.Feedback;
import tech.qijin.cell.message.helper.CellFeedbackHelper;
import tech.qijin.cell.message.service.CellFeedbackService;

import java.util.List;

@Slf4j
@Service
public class CellFeedbackServiceImpl implements CellFeedbackService {
    @Autowired
    private CellFeedbackHelper cellFeedbackHelper;
    @Override
    public boolean sendFeedback(Long userId, String text, List<String> images) {
        Feedback feedback = new Feedback();
        feedback.setUserId(userId);
        feedback.setText(text);
        return cellFeedbackHelper.addFeedback(feedback, images);
    }
}
