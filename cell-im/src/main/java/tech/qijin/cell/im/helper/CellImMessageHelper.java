package tech.qijin.cell.im.helper;

import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.model.ImMessage;

import java.util.List;
import java.util.Optional;

public interface CellImMessageHelper {
    ImMessage convertMessage(MessageSendVO messageSendVO);

    /**
     * 保存消息
     *
     * @param imMessageInfo
     * @return
     */
    boolean saveMessage(ImMessage imMessageInfo);

    Optional<ImMessage> getMessageByMsgId(Long msgId);

    Optional<ImMessage> getMessageByUidAndMsgId(Long uid, Long peerUid, Long msgId);

    List<ImMessage> pageMessage(Long uid, Long peerUid, Long maxMsgId, Long minMsgId, int count);

    boolean updateMessageStatus(long msgId, int originStatus, int toStatus);

    Optional<ImMessage> getPreMessage(long uid, long peerUid, long msgId, long minMsgId);
}
