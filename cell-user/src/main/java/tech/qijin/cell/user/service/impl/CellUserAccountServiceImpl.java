package tech.qijin.cell.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.*;
import tech.qijin.cell.user.db.model.UserAccount;
import tech.qijin.cell.user.helper.CellUserAccountHelper;
import tech.qijin.cell.user.service.CellUserAccountService;
import tech.qijin.cell.user.service.CellUserTokenService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.ValidationUtil;

import java.util.Optional;

/**
 * @author michealyang
 * @date 2020-01-05
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class CellUserAccountServiceImpl implements CellUserAccountService {
    @Autowired
    private CellUserAccountHelper cellUserAccountHelper;
    @Autowired
    private CellUserTokenService cellUserTokenService;

    @Override
    public UserSessionBo register(RegisterType registerType,
                                  AbstractRegisterVo abstractRegisterVo,
                                  boolean login,
                                  int expire) {
        // 校验参数
        ValidationUtil.validate(abstractRegisterVo);

        String userName = "";
        UserSessionBo userSessionBo = UserSessionBo.builder().build();
        switch (registerType) {
            case EMAIL:
                userName = ((EmailRegisterVo) abstractRegisterVo).getEmail();
                userSessionBo = registerForUserName(userName, abstractRegisterVo.getPassword());
                break;
            case MOBILE:
                userName = ((MobileRegisterVo) abstractRegisterVo).getMobile();
                userSessionBo = registerForUserName(userName, abstractRegisterVo.getPassword());
                break;
            case MINI_WECHAT:
                break;
            default:
                MAssert.isTrue(false, ResEnum.INVALID_PARAM);
        }


        if (login) {
            userSessionBo.setUserToken(cellUserTokenService.genUserToken(userSessionBo.getUserAccount().getId(), expire * DateUtil.SECONDS_PER_DAY));
        }
        return userSessionBo;
    }

    private UserSessionBo registerForUserName(String userName, String password) {
        // 检查唯一性
        MAssert.isTrue(!cellUserAccountHelper.getUserAccountByUserName(userName).isPresent(), Constants.UserBuzCode.DUPLICATED);
        // 创建账户
        UserAccount userAccount = new UserAccount();
        userAccount.setRegisterType(RegisterType.EMAIL);
        userAccount.setStatus(UserStatus.NORMAL);
        userAccount.setUserName(userName);
        userAccount.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(11)));
        userAccount = cellUserAccountHelper.saveUserAccount(userAccount);
        return UserSessionBo.builder()
                .userAccount(userAccount)
                .build();
    }

    private UserSessionBo registerForMiniWeChat(String code) {
        return null;
    }

    @Override
    public UserSessionBo login(RegisterType registerType, AbstractRegisterVo abstractRegisterVo, int expire) {
        // 校验参数
        ValidationUtil.validate(abstractRegisterVo);

        String userName = "";
        switch (registerType) {
            case EMAIL:
                ValidationUtil.validate((EmailRegisterVo) abstractRegisterVo);
                userName = ((EmailRegisterVo) abstractRegisterVo).getEmail();
                break;
            case MOBILE:
                ValidationUtil.validate((MobileRegisterVo) abstractRegisterVo);
                userName = ((MobileRegisterVo) abstractRegisterVo).getMobile();
                break;
            default:
                MAssert.isTrue(false, ResEnum.INVALID_PARAM);
        }

        Optional<UserAccount> userAccount = cellUserAccountHelper.getUserAccountByUserName(userName);
        MAssert.isTrue(userAccount.isPresent(), Constants.UserBuzCode.USER_WRONG);
        MAssert.isTrue(BCrypt.checkpw(abstractRegisterVo.getPassword(),
                userAccount.get().getPassword()), Constants.UserBuzCode.USER_WRONG);
        UserToken userToken = cellUserTokenService.genUserToken(userAccount.get().getId(), expire);
        return UserSessionBo.builder()
                .userAccount(userAccount.get())
                .userToken(userToken)
                .build();
    }
}
