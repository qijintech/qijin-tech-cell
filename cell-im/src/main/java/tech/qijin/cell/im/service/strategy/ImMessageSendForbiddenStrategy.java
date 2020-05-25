package tech.qijin.cell.im.service.strategy;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.service.bo.MessageBO;
import tech.qijin.util4j.utils.MAssert;

@Service
public class ImMessageSendForbiddenStrategy implements IMessageSendStrategy {
    @Override
    public MessageBO sendMessage(MessageSendVO messageSendVO, Judgement judgement) {
        MAssert.isTrue(false, judgement.getBuzCode(), judgement.getMessage());
        return MessageBO.builder().build();
    }
}
