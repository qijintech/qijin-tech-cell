package tech.qijin.cell.im.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Date;

@Service
public class IDGenerator {
    @Autowired
    private RedisUtil redisUtil;
    @Value("${cell.im.msg.id.step:10000}")
    private Long msgIdStep;
    @Value("${cell.im.version.step:1000}")
    private Long versionStep;

    /**
     * 返回消息ID
     *
     * @return
     */
    public Long genMsgId(Date now) {
        long count = redisUtil.increment(getMsgIdKey(), 1);
        if (count == 1) {
            redisUtil.setExpire(getMsgIdKey(), 100);
        }
        return now.getTime() * 100 * msgIdStep + count;
    }

    /**
     * 返回消息序列号
     *
     * @return
     */
    public Long genSeqId(Date now) {
        return now.getTime();
    }

    public Long genVersionId(Long uid, Date now) {
        long count = redisUtil.increment(getVersionIdKey(uid), 1);
        if (count == 1) {
            redisUtil.setExpire(getVersionIdKey(uid), 100);
        }
        return now.getTime() * 100 * versionStep + count;
    }

    private String getMsgIdKey() {
        return "im:msg:id";
    }

    private String getVersionIdKey(Long uid) {
        return String.format("%s:%d", "im:version:id:", uid);
    }
}
