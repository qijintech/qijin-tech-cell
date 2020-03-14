package tech.qijin.cell.im.service;

/**
 * 未读数 Domain
 *
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellIMUnreadService {

    // 增加未读数
    int addUnread(long uid, long peerUid, int count);

    // 清除未读数
    boolean clearUnread(long uid, long peerUid, Long lastMsgId);
}
