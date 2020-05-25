package tech.qijin.cell.im.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.helper.ImConversationHelper;
import tech.qijin.cell.im.helper.ImMessageHelper;
import tech.qijin.cell.im.helper.judge.ImJudgeChain;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.service.CellIMMessageService;
import tech.qijin.cell.im.service.bo.MessageBO;
import tech.qijin.cell.im.service.strategy.ImMessageSendStrategyFactory;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class CellIMMessageServiceImpl implements CellIMMessageService {
    @Autowired
    private ImMessageHelper imMessageHelper;
    @Autowired
    private ImConversationHelper imConversationHelper;
    @Autowired
    private ImJudgeChain imJudgeChain;
    @Autowired
    private ImMessageSendStrategyFactory imMessageSendStrategyFactory;


    @Override
    public MessageBO sendMessage(MessageSendVO messageSendVO) {
        ValidationUtil.validate(messageSendVO);
        Judgement judgement = imJudgeChain.doJudge(messageSendVO);
        return imMessageSendStrategyFactory
                .getStrategy(judgement)
                .sendMessage(messageSendVO, judgement);
    }

    @Override
    public List<MessageBO> listUnreadMessage(Long uid, Long peerUid, Long lastMsgId, Integer count) {
        MAssert.notNull(peerUid, ResEnum.INVALID_PARAM);
        Optional<ImConversation> imConversation = imConversationHelper.getConversationByUid(uid, peerUid);
        if (!imConversation.isPresent()) {
            log.info("conversation not exists. uid={}, peerUid={}", uid, peerUid);
            return Collections.EMPTY_LIST;
        }
        if (NumberUtil.nullOrZero(count)) {
            count = Constants.DEFAULT_PAGE_SIZE;
        }
        long minMsgId = lastMsgId != null ? lastMsgId : 0;
        if (!NumberUtil.nullOrZero(imConversation.get().getLastClearMsg())) {
            minMsgId = imConversation.get().getLastClearMsg().compareTo(minMsgId) > 0
                    ? imConversation.get().getLastClearMsg()
                    : minMsgId;
        }
        return imMessageHelper.pageMessage(uid, peerUid, Long.MAX_VALUE, minMsgId, count)
                .stream()
                .map(imMessage -> MessageBO.builder().imMessage(imMessage).build())
                .collect(Collectors.toList());
    }

    @Override
    public List<MessageBO> listHistoryMessage(Long uid, Long peerUid, Long lastMsgId, Integer count) {
        MAssert.notNull(peerUid, ResEnum.INVALID_PARAM);
        Optional<ImConversation> imConversation = imConversationHelper.getConversationByUid(uid, peerUid);
        if (!imConversation.isPresent()) {
            log.info("conversation not exists. uid={}, peerUid={}", uid, peerUid);
            return Collections.EMPTY_LIST;
        }
        if (NumberUtil.nullOrZero(lastMsgId)) {
            lastMsgId = Long.MAX_VALUE;
        }
        if (NumberUtil.nullOrZero(count)) {
            count = Constants.DEFAULT_PAGE_SIZE;
        }
        return imMessageHelper.pageMessage(uid, peerUid, lastMsgId, imConversation.get().getLastClearMsg(), count)
                .stream()
                .map(imMessage -> MessageBO.builder().imMessage(imMessage).build())
                .collect(Collectors.toList());
    }

    /**
     * 删除消息
     * <p>
     * 消息状态：
     * <ul>
     * <li>0(00): 正常消息</li>
     * <li>1(01): smaller uid删除的消息</li>
     * <li>2(10): larger uid删除的消息</li>
     * <li>3(11): 二者均删除的消息</li>
     * <li>4(100): 撤回的消息</li>
     * </ul>
     * </p>
     *
     * @param uid
     * @param msgId
     * @return
     */
    @Override
    public boolean delMessage(Long uid, Long msgId) {
        return false;
    }

    @Override
    public boolean recallMessage(Long uid, Long msgId) {
        return false;
    }
}
