package tech.qijin.cell.im.service;

import tech.qijin.cell.im.service.bo.MessageBO;

/**
 * 会话 Domain
 *
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellIMConversationService {


    /**
     * 删除会话
     *
     * @param uid
     * @param peerUid
     * @return
     */
    boolean deleteConversation(Long uid, Long peerUid);
}
