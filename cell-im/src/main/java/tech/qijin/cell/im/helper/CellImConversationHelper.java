package tech.qijin.cell.im.helper;

import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImMessage;

import java.util.List;
import java.util.Optional;

public interface CellImConversationHelper {
    Optional<ImConversation> getConversationByUid(long uid, long peerUid);

    /**
     * 插入或者更新会话
     * <p>当会话不存在时，插入会话，否则更新会话</p>
     *
     * @param uid
     * @param peerUid
     * @param imMessage
     * @return
     */
    ImConversation insertOrUpdateConversation(long uid, long peerUid, ImMessage imMessage);

    List<ImConversation> pageConversation(long uid, long minVersionId, long maxVersionId, int count);
}
