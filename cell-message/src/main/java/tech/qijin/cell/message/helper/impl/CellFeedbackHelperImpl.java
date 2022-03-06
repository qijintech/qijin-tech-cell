package tech.qijin.cell.message.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.message.db.dao.FeedbackDao;
import tech.qijin.cell.message.db.dao.FeedbackImageDao;
import tech.qijin.cell.message.db.model.Feedback;
import tech.qijin.cell.message.db.model.FeedbackImage;
import tech.qijin.cell.message.helper.CellFeedbackHelper;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellFeedbackHelperImpl implements CellFeedbackHelper {
    @Autowired
    private FeedbackDao feedbackDao;
    @Autowired
    private FeedbackImageDao feedbackImageDao;


    @Override
    public boolean addFeedback(Feedback feedback, List<String> images) {
        String check = check(feedback, images);
        if (StringUtils.isNotBlank(check)){
            log.error("addFeedback check error, check={}", check);
            return false;
        }
        if (feedbackDao.insertSelective(feedback) > 0 && CollectionUtils.isNotEmpty(images)) {
            List<FeedbackImage> feedbackImages = images.stream().map(image -> {
                FeedbackImage feedbackImage = new FeedbackImage();
                feedbackImage.setFeedbackId(feedback.getId());
                feedbackImage.setUrl(image);
                return feedbackImage;
            }).collect(Collectors.toList());
            feedbackImageDao.batchInsert(feedbackImages);
        }
        return true;
    }

    private String check(Feedback feedback, List<String> images) {
        if (!NumberUtil.gtZero(feedback.getUserId())) return "userId";
        if (StringUtils.isBlank(feedback.getText()) && CollectionUtils.isEmpty(images)) return "text or images";
        return "";
    }
}
