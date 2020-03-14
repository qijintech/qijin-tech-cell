package tech.qijin.cell.user.base;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public enum RegisterType {
    OAUTH_WECHAT("OAuth-微信"),
    MINI_WECHAT("小程序-微信"),
    MINI_DOUYIN("小程序-抖音"),
    SELF("自建注册"),
    ;

    RegisterType(String description) {
        this.description = description;
    }

    private String description;
}
