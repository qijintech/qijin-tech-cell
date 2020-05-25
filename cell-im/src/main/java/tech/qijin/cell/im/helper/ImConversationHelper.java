package tech.qijin.cell.im.helper;

import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImMessage;

import java.util.Optional;

public interface ImConversationHelper {
    Optional<ImConversation> getConversationByUid(Long uid, Long peerUid);

    /**
     * 插入或者更新会话
     * <p>当会话不存在时，插入会话，否则更新会话</p>
     *
     * @param uid
     * @param peerUid
     * @param imMessage
     * @return
     */
    ImConversation insertOrUpdateConversation(Long uid, Long peerUid, ImMessage imMessage);
}
