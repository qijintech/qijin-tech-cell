package tech.qijin.cell.im.service;

import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.service.bo.MessageBO;

import java.util.List;

/**
 * 会话 Domain
 *
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellImConversationService {

    /**
     * 历史会话列表
     *
     * @param uid
     * @param versionId
     * @param count
     * @return
     */
    List<ImConversation> listConversationHistory(Long uid, Long versionId, Integer count);

    /**
     * 新增会话列表
     *
     * @param uid
     * @param versionId
     * @param count
     * @return
     */
    List<ImConversation> listConversationNew(Long uid, Long versionId, Integer count);

    /**
     * 清空会话聊天消息
     *
     * @param uid
     * @param peerUid
     * @return
     */
    boolean clearConversation(Long uid, Long peerUid);


    /**
     * 删除会话
     *
     * @param uid
     * @param peerUid
     * @return
     */
    boolean deleteConversation(Long uid, Long peerUid);
}
