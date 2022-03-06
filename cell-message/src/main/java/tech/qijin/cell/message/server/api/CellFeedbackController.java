package tech.qijin.cell.message.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.cell.message.base.MessageBo;
import tech.qijin.cell.message.base.MessageWrapper;
import tech.qijin.cell.message.server.vo.FeedbackReqVo;
import tech.qijin.cell.message.server.vo.MessageReqVo;
import tech.qijin.cell.message.server.vo.MessageVo;
import tech.qijin.cell.message.server.vo.MessagesVo;
import tech.qijin.cell.message.service.CellFeedbackService;
import tech.qijin.cell.message.service.CellMessageService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/feedback/")
public class CellFeedbackController {
    @Autowired
    private CellFeedbackService cellFeedbackService;

    @PostMapping("/send")
    public Boolean sendFeedback(@RequestBody FeedbackReqVo reqVo) {
        return cellFeedbackService.sendFeedback(UserUtil.getUserId(), reqVo.getText(), reqVo.getImages());
    }
}
