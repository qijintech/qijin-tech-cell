package tech.qijin.cell.im.service;

import tech.qijin.cell.im.service.bo.MessageBo;

/**
 * 会话 Domain
 *
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellIMConversationService {
    /**
     * 插入或者更新会话
     * <p>当会话不存在时，插入会话，否则更新会话</p>
     *
     * @param uid
     * @param peerUid
     * @param lastMessage
     * @return
     */
    boolean insertOrUpdateConversation(Long uid, Long peerUid, MessageBo lastMessage);

    /**
     * 删除会话
     *
     * @param uid
     * @param peerUid
     * @return
     */
    boolean deleteConversation(Long uid, Long peerUid);
}
