package tech.qijin.cell.user.service;

import tech.qijin.cell.user.base.AbstractRegisterVo;
import tech.qijin.cell.user.base.AccountType;
import tech.qijin.cell.user.base.UserSessionBo;

/**
 * @author michealyang
 * @date 2019-12-10
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellUserAccountService {

    UserSessionBo register(AccountType accountType, AbstractRegisterVo abstractRegisterVo, boolean login, int expire);

    UserSessionBo login(AccountType accountType, AbstractRegisterVo abstractRegisterVo, int expire);

    void sendCaptcha(String mobile);
}
