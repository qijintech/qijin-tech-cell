package tech.qijin.cell.im.service.strategy;

import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.service.bo.MessageBO;

public interface IMessageSendStrategy {
    MessageBO sendMessage(MessageSendVO messageSendVO, Judgement judgement);
}
