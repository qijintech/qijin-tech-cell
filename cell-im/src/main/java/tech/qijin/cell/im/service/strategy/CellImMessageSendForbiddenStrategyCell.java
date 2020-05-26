package tech.qijin.cell.im.service.strategy;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.util4j.utils.MAssert;

@Service
public class CellImMessageSendForbiddenStrategyCell implements CellIMessageSendStrategy {
    @Override
    public ImMessage sendMessage(MessageSendVO messageSendVO, Judgement judgement) {
        MAssert.isTrue(false, judgement.getBuzCode(), judgement.getMessage());
        return new ImMessage();
    }
}
