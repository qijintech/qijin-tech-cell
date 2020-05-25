package tech.qijin.cell.im.service;

import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.service.bo.MessageBO;

import java.util.List;

/**
 * 消息 Domain
 *
 * @author michealyang
 * @date 2019-11-04
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellIMMessageService {
    /**
     * 发送消息
     *
     * @param messageSendVo
     * @return msgId
     */
    MessageBO sendMessage(MessageSendVO messageSendVo);

    /**
     * 未读消息列表
     *
     * @param uid
     * @param peerUid
     * @param lastMsgId
     * @param count     获取数量
     * @return
     */
    List<MessageBO> listUnreadMessage(Long uid, Long peerUid, Long lastMsgId, Integer count);

    /**
     * 历史消息列表
     *
     * @param uid
     * @param peerUid
     * @param lastMsgId
     * @param count
     * @return
     */
    List<MessageBO> listHistoryMessage(Long uid, Long peerUid, Long lastMsgId, Integer count);

    /**
     * 删除消息
     *
     * @return
     */
    boolean delMessage(Long uid, Long peerUid, Long msgId);

    /**
     * 撤回消息
     *
     * @return
     */
    boolean recallMessage(Long uid, Long peerUid, Long msgId);

    /**
     * 设置消息的已读状态
     *
     * @param uid
     * @param msgId
     * @return
     */
    boolean readMessage(Long uid, Long peerUid, Long msgId);
}