package tech.qijin.cell.user.service;

import tech.qijin.cell.user.base.UserToken;

/**
 * @author michealyang
 * @date 2019-12-10
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellUserTokenService {
    /**
     *
     * @param userId
     * @param expire 单位秒
     * @return
     */
    UserToken genUserToken(Long userId, int expire);

    Long auth(String token);
}
