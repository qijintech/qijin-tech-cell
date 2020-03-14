package tech.qijin.cell.im.service;

import tech.qijin.cell.im.base.MessageSendVo;
import tech.qijin.cell.im.service.bo.MessageBo;

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
    Long sendMessage(MessageSendVo messageSendVo);

    /**
     * 未读消息列表
     *
     * @param uid
     * @param peerUid
     * @param lastMsgId
     * @param count     获取数量
     * @return
     */
    List<MessageBo> listUnreadMessage(Long uid, Long peerUid, Long lastMsgId, Integer count);

    /**
     * 历史消息列表
     *
     * @param uid
     * @param peerUid
     * @param lastMsgId
     * @param count
     * @return
     */
    List<MessageBo> listHistoryMessage(Long uid, Long peerUid, Long lastMsgId, Integer count);

    /**
     * 删除消息
     *
     * @return
     */
    boolean delMessage(Long uid, Long msgId);

    /**
     * 撤回消息
     *
     * @return
     */
    boolean recallMessage(Long uid, Long msgId);
}