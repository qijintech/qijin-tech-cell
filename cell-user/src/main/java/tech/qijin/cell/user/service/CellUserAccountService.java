package tech.qijin.cell.user.service;

import tech.qijin.cell.user.base.AbstractRegisterVo;
import tech.qijin.cell.user.base.RegisterType;
import tech.qijin.cell.user.base.UserSessionBo;

/**
 * @author michealyang
 * @date 2019-12-10
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellUserAccountService {

    UserSessionBo register(RegisterType registerType, AbstractRegisterVo abstractRegisterVo, boolean login, int expire);

    UserSessionBo login(RegisterType registerType, AbstractRegisterVo abstractRegisterVo, int expire);
}
