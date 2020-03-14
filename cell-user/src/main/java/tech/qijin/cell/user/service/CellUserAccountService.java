package tech.qijin.cell.user.service;

/**
 * @author michealyang
 * @date 2019-12-10
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellUserAccountService {
    // 自定义注册
    void registerBySelfDefined();

    void registerByMiniWeChat(String code);

    void registerByOAuth();
}
