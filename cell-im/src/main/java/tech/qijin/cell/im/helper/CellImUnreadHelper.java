package tech.qijin.cell.im.helper;

public interface CellImUnreadHelper {
    /**
     * 增加未读数
     *
     * @param uid
     * @param peerUid
     * @param count
     * @return 总未读数
     */
    Long incrUnread(long uid, long peerUid, int count);

    void clearUnread(long uid, long peerUid);
}
