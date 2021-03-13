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
        ACCOUNT_MISMATCH(12002, "用户名或密码错误"),
        DUPLICATE_ACCOUNT(12003, "账号已存在"),
        DUPLICATE_NICKNAME(12004, "昵称已存在"),
        EMPTY_USERNAME(12005, "用户名不能为空"),
        EMPTY_PASSWORD(12006, "密码不能为空"),
        FORBIDDEN(12007, "禁止操作"),
        EMPTY_CAPTCHA(12008, "验证码不能为空"),
        EMPTY_MOBILE(12009, "手机号不能为空"),
        CAPTCHA_MISMATCH(12010, "验证码不正确或已失效"),
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
