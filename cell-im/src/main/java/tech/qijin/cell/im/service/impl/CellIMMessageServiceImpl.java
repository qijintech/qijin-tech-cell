package tech.qijin.cell.im.service.impl;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVo;
import tech.qijin.cell.im.db.model.ImMessageContent;
import tech.qijin.cell.im.db.model.ImMessageInfo;
import tech.qijin.cell.im.service.CellIMConversationService;
import tech.qijin.cell.im.service.CellIMMessageService;
import tech.qijin.cell.im.service.CellIMUnreadService;
import tech.qijin.cell.im.service.bo.MessageBo;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.*;

import java.util.Date;
import java.util.List;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class CellIMMessageServiceImpl implements CellIMMessageService {
    @Autowired
    private CellIMConversationService conversationService;
    @Autowired
    private CellIMUnreadService unreadService;


    @Override
    public Long sendMessage(MessageSendVo messageSendVo) {
        MAssert.isTrue(ValidationUtil.isValid(messageSendVo), ResEnum.INVALID_PARAM);
        Date now = DateUtil.now();
        // 插入消息
        MessageBo messageBo = insertMessage(messageSendVo, now);
        // 更新会话信息 - 忽略异常
        Util.runIgnoreEx(() -> conversationService.insertOrUpdateConversation(messageSendVo.getUid(), messageSendVo.getToUid(), messageBo),
                "insert or update conversation failed");
        // 更新未读数 - 异步更新
        AsyncUtil.submit(() -> unreadService.addUnread(messageSendVo.getUid(), messageSendVo.getToUid(), 1));
        return null;
    }


    @Override
    public List<MessageBo> listUnreadMessage(Long uid, Long peerUid, Long lastMsgId, Integer count) {
        return null;
    }

    @Override
    public List<MessageBo> listHistoryMessage(Long uid, Long peerUid, Long lastMsgId, Integer count) {
        // 要排除删除的消息
        return null;
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


    private MessageBo insertMessage(MessageSendVo messageSendVo, Date now) {
        // 获取msgId，versionId等
        MessageIds messageIds = genMessageIds(messageSendVo.getUid(), messageSendVo.getToUid(), now);
        // 插入消息信息
        ImMessageInfo messageInfo = insertOrUpdateMessageInfo(messageSendVo, messageIds);
        // 插入消息内容实体
        ImMessageContent messageContent = insertMessageContent(messageSendVo, messageIds.getMsgId());
        // 更新缓存。出现异常不影响主流程
        Util.runIgnoreEx(() -> refreshMessageCache(), "refresh message cache fail");
        return MessageBo.builder()
                .messageInfo(messageInfo)
                .messageContent(messageContent)
                .build();
    }

    @Data
    @Builder
    class MessageIds {
        private Long msgId;
        private Long versionId;
    }

    private MessageIds genMessageIds(Long uid, Long peerUid, Date now) {
        Long msgId = genMsgId(uid, peerUid, now);
        Long versionId = genVersionId(uid, peerUid, now);
        return MessageIds.builder()
                .msgId(msgId)
                .versionId(versionId)
                .build();
    }

    private Long genMsgId(Long uid, Long peerUid, Date now) {
        return 0L;
    }

    private Long genVersionId(Long uid, Long peerUid, Date now) {
        return 0L;
    }

    private ImMessageContent insertMessageContent(MessageSendVo messageSendVo, Long msgId) {
        return null;
    }

    private ImMessageInfo insertOrUpdateMessageInfo(MessageSendVo messageSendVo, MessageIds messageIds) {
        return null;
    }

    private void refreshMessageCache() {
    }
}
