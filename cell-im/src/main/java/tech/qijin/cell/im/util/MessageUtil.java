package tech.qijin.cell.im.util;

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
}
