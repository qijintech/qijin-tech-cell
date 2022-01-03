package tech.qijin.cell.message.server.vo;

import lombok.Data;
import tech.qijin.cell.message.base.MessageKind;

@Data
public class MessageReqVo {
    private MessageKind kind;
    private String brief;
    private String content;
}
