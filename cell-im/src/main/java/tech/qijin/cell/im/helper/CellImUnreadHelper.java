package tech.qijin.cell.im.helper;

public interface CellImUnreadHelper {
    // 增加未读数
    int incrUnread(long uid, long peerUid, int count);
}
