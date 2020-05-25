package tech.qijin.cell.im.base;

public class Constants {

    // 默认分页大小
    public static final int DEFAULT_PAGE_SIZE = 20;

    /**
     * 业务错误码
     */
    public static enum BuzCode {
        FORBIDDEN(11001, "禁止发消息"),
        SENSITIVE(11002, "有敏感信息"),
        ;

        int code;
        String message;

        BuzCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }
    }
}
