package tech.qijin.cell.im.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.CellImConversationHelper;
import tech.qijin.cell.im.helper.CellImMessageHelper;
import tech.qijin.cell.im.helper.CellImUnreadHelper;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.AsyncUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.Util;

@Service
public class CellImMessageSendNormalStrategyCell implements CellIMessageSendStrategy {
    @Autowired
    private CellImMessageHelper messageHelper;
    @Autowired
    private CellImConversationHelper conversationHelper;
    @Autowired
    private CellImUnreadHelper unreadHelper;

    @Override
    public ImMessage sendMessage(MessageSendVO messageSendVO, Judgement judgement) {
        // 插入消息
        ImMessage imMessage = messageHelper.convertMessage(messageSendVO);
        MAssert.isTrue(messageHelper.saveMessage(imMessage), ResEnum.INTERNAL_ERROR);
        //
        // 更新自己的会话信息 - 忽略异常
        Util.runIgnoreEx(() -> conversationHelper.insertOrUpdateConversation(messageSendVO.getUid(), messageSendVO.getToUid(), imMessage),
                "insert or update conversation failed");
        // 更新对方的会话信息 - 忽略异常
        Util.runIgnoreEx(() -> conversationHelper.insertOrUpdateConversation(messageSendVO.getToUid(), messageSendVO.getUid(), imMessage),
                "insert or update conversation failed");
        // 更新未读数 - 异步更新
        AsyncUtil.submit(() -> unreadHelper.incrUnread(messageSendVO.getUid(), messageSendVO.getToUid(), 1));

        return imMessage;
    }
}
