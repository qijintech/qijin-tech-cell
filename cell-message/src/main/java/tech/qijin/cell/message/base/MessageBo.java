package tech.qijin.cell.message.base;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.message.db.model.Message;
import tech.qijin.cell.message.db.model.MessageContent;
import tech.qijin.cell.message.db.model.MessageDrops;

import java.util.List;

@Data
@Builder
public class MessageBo {
    private Message message;
    private MessageContent messageContent;
    private List<MessageDrops> drops;
}
