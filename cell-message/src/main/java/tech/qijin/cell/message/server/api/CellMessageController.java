package tech.qijin.cell.message.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.cell.message.base.MessageBo;
import tech.qijin.cell.message.base.MessageWrapper;
import tech.qijin.cell.message.server.vo.MessageReqVo;
import tech.qijin.cell.message.server.vo.MessageVo;
import tech.qijin.cell.message.server.vo.MessagesVo;
import tech.qijin.cell.message.service.CellMessageService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message/")
public class CellMessageController {
    @Autowired
    private CellMessageService cellMessageService;

    @GetMapping("/count")
    public MessagesVo getMessage() {
        return MessagesVo.builder()
                .count(cellMessageService.countMessage(UserUtil.getUserId()))
                .unreadCount(cellMessageService.countUnread(UserUtil.getUserId()))
                .build();
    }

    @GetMapping("/list")
    public MessagesVo listMessage(PageVo pageVo) {
        List<MessageBo> messageBos = cellMessageService.pageMessage(UserUtil.getUserId(), pageVo);
        return MessagesVo.builder()
                .message(MessageVo.from(messageBos))
                .hasMore(cellMessageService.hasMore(messageBos, pageVo))
                .build();
    }

    @PostMapping("/send")
    public boolean sendMessage(@RequestBody MessageReqVo reqVo) {
        MAssert.notNull(reqVo, ResEnum.INVALID_PARAM);
        MAssert.notNull(reqVo.getKind(), ResEnum.INVALID_PARAM);
        MAssert.notBlank(reqVo.getContent(), ResEnum.INVALID_PARAM);
        MessageWrapper messageWrapper = MessageWrapper.builder()
                .kind(reqVo.getKind())
                .content(reqVo.getContent())
                .brief(reqVo.getBrief())
                .build();
        return cellMessageService.sendMessage(UserUtil.getUserId(), messageWrapper);
    }
}
