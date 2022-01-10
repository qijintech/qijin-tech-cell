package tech.qijin.cell.message.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.message.base.MessageBo;
import tech.qijin.cell.message.base.MessageKind;
import tech.qijin.cell.message.base.MessageStatus;
import tech.qijin.cell.message.db.model.Message;
import tech.qijin.util4j.trace.pojo.Channel;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class MessageVo {
    private Long id;

    private MessageKind kind;

    private String title;

    private String icon;

    private String brief;

    private Boolean read;

    private String content;

    private Date createTime;

    private String createTimeStr;

    public static MessageVo from(MessageBo messageBo) {
        MessageVo messageVo = ConvertUtil.convert(messageBo.getMessage(), MessageVo.class);
        if (messageVo != null) {
            Message message = messageBo.getMessage();
            messageVo.setIcon(message.getKind().icon());
            messageVo.setTitle(message.getKind().title());
            messageVo.setContent(messageBo.getMessageContent().getContent());
            messageVo.setCreateTimeStr(DateUtil.formatStr(message.getCreateTime(), DateUtil.DATETIME_FORMAT));
        }
        return messageVo;
    }

    public static List<MessageVo> from(List<MessageBo> messageBos) {
        if (CollectionUtils.isEmpty(messageBos)) return Collections.emptyList();
        return messageBos.stream().map(MessageVo::from).collect(Collectors.toList());
    }
}
