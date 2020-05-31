package tech.qijin.cell.user.base;

import tech.qijin.util4j.lang.constant.BuzCode;

public class Constants {

    // 默认分页大小
    public static final int DEFAULT_PAGE_SIZE = 20;
    public static final int MAX_PAGE_SIZE = 100;

    /**
     * 业务错误码
     */
    public static enum UserBuzCode implements BuzCode {
        INVALID_TOKEN(12001, "token已失效"),
        USER_WRONG(12002, "用户名或密码错误"),

        DUPLICATED(12003, "重复的账号"),

        FORBIDDEN(12006, "禁止操作"),
        ;


        int code;
        String message;

        UserBuzCode(int code, String message) {
            this.code = code;
            this.message = message;
        }

        @Override
        public int code() {
            return this.code;
        }

        @Override
        public String message() {
            return this.message;
        }
    }
}
