package tech.qijin.cell.im.helper.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.helper.CellImUnreadHelper;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.NumberUtil;

@Service
public class CellImUnreadHelperImpl implements CellImUnreadHelper {
    private static final String UNREAD_SINGLE_KEY = "cell:im:unread:single";
    private static final String UNREAD_TOTAL_KEY = "cell:im:unread:total";
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Long incrUnread(long uid, long peerUid, int count) {
        incrSingleUnread(uid, peerUid, count);
        return incrTotalUnread(uid, count);
    }

    @Override
    public void clearUnread(long uid, long peerUid) {
        long unread = getSingleUnread(uid, peerUid);
        if (unread > 0) {
            clearSingleUnread(uid, peerUid);
            decrTotalUnread(uid, (int) unread);
        }
    }


    private long getSingleUnread(long uid, long peerUid) {
        String key = getSingleUnreadKey(uid);
        Long unread = (Long) redisUtil.hGet(key, String.valueOf(peerUid));
        return NumberUtil.nullOrZero(unread) ? 0 : unread;
    }

    private Long incrSingleUnread(long uid, long peerUid, int count) {
        String key = getSingleUnreadKey(uid);
        return redisUtil.hIncrBy(key, String.valueOf(peerUid), count);
    }

    private Long clearSingleUnread(long uid, long peerUid) {
        String key = getSingleUnreadKey(uid);
        return redisUtil.hDelete(key, String.valueOf(peerUid));
    }

    private Long incrTotalUnread(long uid, int count) {
        String key = getTotalUnreadKey(uid);
        return redisUtil.increment(key, count);
    }

    private Long decrTotalUnread(long uid, int count) {
        String key = getTotalUnreadKey(uid);
        return redisUtil.decrement(key, count);
    }

    private String getSingleUnreadKey(long uid) {
        return String.format("%s:%d", UNREAD_SINGLE_KEY, uid);
    }

    private String getTotalUnreadKey(long uid) {
        return String.format("%s:%d", UNREAD_TOTAL_KEY, uid);
    }
}
