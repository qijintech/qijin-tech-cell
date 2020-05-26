package tech.qijin.cell.im.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.CellImConversationHelper;
import tech.qijin.cell.im.helper.CellImMessageHelper;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.util.MessageUtil;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.Util;

@Service
public class CellImMessageSendSilentStrategyCell implements CellIMessageSendStrategy {
    @Autowired
    private CellImMessageHelper messageHelper;
    @Autowired
    private CellImConversationHelper conversationHelper;

    @Override
    public ImMessage sendMessage(MessageSendVO messageSendVO, Judgement judgement) {
        // 插入消息
        ImMessage imMessage = messageHelper.convertMessage(messageSendVO);
        // 设置消息的status为本方删除
        int status = messageSendVO.getUid() > messageSendVO.getToUid()
                ? MessageUtil.largerDelete(0)
                : MessageUtil.smallerDelete(0);
        imMessage.setStatus(status);
        MAssert.isTrue(messageHelper.saveMessage(imMessage), ResEnum.INTERNAL_ERROR);

        // 更新自己的会话信息 - 忽略异常
        Util.runIgnoreEx(() -> conversationHelper.insertOrUpdateConversation(messageSendVO.getUid(), messageSendVO.getToUid(), imMessage),
                "insert or update conversation failed");
        return imMessage;
    }
}
