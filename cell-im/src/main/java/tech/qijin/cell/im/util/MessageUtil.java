package tech.qijin.cell.im.util;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import tech.qijin.cell.im.base.*;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;

import java.util.Collections;
import java.util.Map;

public class MessageUtil {
    /**
     * status 第0位表示较小uid的删除状态
     * status 第1位表示较大uid的删除状态
     * status 第2位表示撤回状态
     *
     * @param origin
     * @return
     */
    public static int smallerDelete(int origin) {
        return origin | 0x01;
    }

    public static int largerDelete(int origin) {
        return origin | 0x02;
    }

    public static long extractToUid(long fromUid, String unionId) {
        String[] arr = unionId.split(":");
        MAssert.isTrue(arr.length == 2, ResEnum.INTERNAL_ERROR);
        return fromUid == Long.valueOf(arr[0]) ? Long.valueOf(arr[1]) : Long.valueOf(arr[0]);
    }

    public static AbstractContent deserializeContent(MsgType msgType, String content) {
        switch (msgType) {
            case TEXT:
                return JSON.parseObject(content, ContentText.class);
            case IMG:
                return JSON.parseObject(content, ContentImg.class);
            case VIDEO:
                return JSON.parseObject(content, ContentVideo.class);
            case AUDIO:
                return JSON.parseObject(content, ContentAudio.class);
            case STICKER:
                return JSON.parseObject(content, ContentSticker.class);
            case LOCATION:
                return JSON.parseObject(content, ContentLocation.class);
        }
        throw new UnsupportedOperationException(String.format("unsupported msgType: %s", msgType));
    }

    public static Map<String, Object> deserializeExtra(String extra) {
        if (StringUtils.isBlank(extra)) {
            return Collections.EMPTY_MAP;
        }
        return JSON.parseObject(extra);
    }
}
