package tech.qijin.cell.im.service.strategy;

import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.judge.Judgement;

public interface CellIMessageSendStrategy {
    ImMessage sendMessage(MessageSendVO messageSendVO, Judgement judgement);
}
