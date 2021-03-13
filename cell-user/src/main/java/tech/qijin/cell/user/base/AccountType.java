package tech.qijin.cell.user.base;

import tech.qijin.util4j.lang.constant.EnumValue;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum AccountType implements EnumValue<String> {
    OAUTH_WECHAT("OAuth-微信"),
    MINI_WECHAT("小程序-微信"),
    MINI_DOUYIN("小程序-抖音"),
    MOBILE("手机号注册"),
    EMAIL("邮件注册"),
    USERNAME("用户名注册");


    AccountType(String description) {
        this.description = description;
    }

    private String description;

    @Override
    public String value() {
        return this.name();
    }

    @Override
    public String desc() {
        return description;
    }
}
