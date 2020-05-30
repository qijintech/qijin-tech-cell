package tech.qijin.cell.im.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.base.MessageSendBo;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.CellImConversationHelper;
import tech.qijin.cell.im.helper.CellImMessageHelper;
import tech.qijin.cell.im.helper.CellImUnreadHelper;
import tech.qijin.cell.im.service.CellImMessageService;
import tech.qijin.cell.im.base.CellImMessageBo;
import tech.qijin.cell.im.util.MessageUtil;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.*;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class CellImMessageServiceImpl implements CellImMessageService {
    @Autowired
    private CellImMessageHelper cellImMessageHelper;
    @Autowired
    private CellImConversationHelper cellImConversationHelper;
    @Autowired
    private CellImUnreadHelper cellImUnreadHelper;


    @Override
    public ImMessage sendMessage(MessageSendBo messageSendBo) {
        ValidationUtil.validate(messageSendBo);
        // 插入消息
        ImMessage imMessage = cellImMessageHelper.convertMessage(messageSendBo);
        MAssert.isTrue(cellImMessageHelper.saveMessage(imMessage), ResEnum.INTERNAL_ERROR);

        // 更新自己的会话信息 - 忽略异常
        Util.runIgnoreEx(() -> cellImConversationHelper.insertOrUpdateConversation(messageSendBo.getUid(), messageSendBo.getToUid(), imMessage),
                "insert or update conversation failed");
        if (!messageSendBo.isSilent()) {
            // 更新对方的会话信息 - 忽略异常
            Util.runIgnoreEx(() -> cellImConversationHelper.insertOrUpdateConversation(messageSendBo.getToUid(), messageSendBo.getUid(), imMessage),
                    "insert or update conversation failed");
            // 更新未读数 - 异步更新，并忽略异常
            AsyncUtil.submit(
                    () -> Util.runIgnoreEx(
                            () -> cellImUnreadHelper.incrUnread(messageSendBo.getUid(), messageSendBo.getToUid(), 1), "incr unread failed"));
        }
        return imMessage;
    }

    @Override
    public List<CellImMessageBo> listMessageNew(Long uid, Long peerUid, Long lastMsgId, Integer count) {
        MAssert.notNull(peerUid, ResEnum.INVALID_PARAM);
        Optional<ImConversation> imConversation = cellImConversationHelper.getConversationByUid(uid, peerUid);
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
        return cellImMessageHelper.pageMessage(uid, peerUid, Long.MAX_VALUE, minMsgId, count)
                .stream()
                .filter(Objects::nonNull)
                .map(imMessage -> convert(imMessage))
                .collect(Collectors.toList());
    }

    @Override
    public List<CellImMessageBo> listMessageHistory(Long uid, Long peerUid, Long lastMsgId, Integer count) {
        MAssert.notNull(peerUid, ResEnum.INVALID_PARAM);
        Optional<ImConversation> imConversation = cellImConversationHelper.getConversationByUid(uid, peerUid);
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
        return cellImMessageHelper.pageMessage(uid, peerUid, lastMsgId, imConversation.get().getLastClearMsg(), count)
                .stream()
                .filter(Objects::nonNull)
                .map(imMessage -> convert(imMessage))
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
    public boolean delMessage(Long uid, Long peerUid, Long msgId) {
        MAssert.notNull(peerUid, ResEnum.INVALID_PARAM);
        MAssert.notNull(msgId, ResEnum.INVALID_PARAM);
        Optional<ImMessage> imMessage = cellImMessageHelper.getMessageByUidAndMsgId(uid, peerUid, msgId);
        Optional<ImConversation> imConversation = cellImConversationHelper.getConversationByUid(uid, peerUid);
        if (!imMessage.isPresent() || !imConversation.isPresent()) {
            log.warn("message or conversation not exists. uid={}, peerUid={}, messageId={}, imMessage={}, imConversatoin={}",
                    uid, peerUid, msgId, imMessage, imConversation);
            return false;
        }
        int status = 0;
        if (uid.compareTo(peerUid) > 0) {
            status = MessageUtil.largerDelete(imMessage.get().getStatus());
        } else {
            status = MessageUtil.smallerDelete(imMessage.get().getStatus());
        }
        // 更新message状态
        if (!cellImMessageHelper.updateMessageStatus(msgId, imMessage.get().getStatus(), status)) {
            return false;
        }
        // 更新conversation最后一条消息
        if (msgId < imConversation.get().getLastMsgId()) {
            // do nothing
            return true;
        }
        // 如果删除的是最后一条消息，需要把上一条消息给补充上
        Optional<ImMessage> preMessageOpt = cellImMessageHelper.getPreMessage(uid, peerUid, msgId, imConversation.get().getLastClearMsg());
        ImMessage preMessage;
        if (preMessageOpt.isPresent()) {
            preMessage = preMessageOpt.get();
        } else {
            preMessage = new ImMessage();
        }
        Util.runIgnoreEx(() -> cellImConversationHelper.insertOrUpdateConversation(uid, peerUid, preMessage), "update conversation failed");
        return true;
    }

    @Override
    public boolean recallMessage(Long uid, Long peerUid, Long msgId) {
        return false;
    }

    @Override
    public boolean readMessage(Long uid, Long peerUid, Long msgId) {
        return false;
    }

    private CellImMessageBo convert(ImMessage imMessage) {
        return CellImMessageBo.builder()
                .imMessage(imMessage)
                .toUid(MessageUtil.extractToUid(imMessage.getFromUid(), imMessage.getUnionId()))
                .content(MessageUtil.deserializeContent(imMessage.getMsgType(), imMessage.getContent()))
                .extra(MessageUtil.deserializeExtra(imMessage.getExtra()))
                .build();
    }
}
