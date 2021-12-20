package tech.qijin.cell.message.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.message.base.MessageBo;
import tech.qijin.cell.message.server.vo.MessageVo;
import tech.qijin.cell.message.server.vo.MessagesVo;
import tech.qijin.cell.message.service.CellMessageService;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message/")
public class CellMessageController {
    @Autowired
    private CellMessageService cellMessageService;

    @ResponseBody
    @RequestMapping("/list")
    public MessagesVo listMessage(PageVo pageVo) {
        List<MessageBo> messageBos = cellMessageService.pageMessage(UserUtil.getUserId(), pageVo);
        return MessagesVo.builder()
                .message(MessageVo.from(messageBos))
                .hasMore(cellMessageService.hasMore(messageBos, pageVo))
                .build();
    }
}
