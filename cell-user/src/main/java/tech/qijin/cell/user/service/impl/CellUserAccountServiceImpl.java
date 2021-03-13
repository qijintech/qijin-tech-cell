package tech.qijin.cell.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.*;
import tech.qijin.cell.user.db.model.UserAccount;
import tech.qijin.cell.user.helper.CellUserAccountHelper;
import tech.qijin.cell.user.service.CellUserAccountService;
import tech.qijin.cell.user.service.CellUserTokenService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.CaptchaUtil;
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
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public UserSessionBo register(AccountType accountType,
                                  AbstractRegisterVo abstractRegisterVo,
                                  boolean login,
                                  int expire) {
        // 校验参数
        ValidationUtil.validate(abstractRegisterVo);


        UserSessionBo userSessionBo = UserSessionBo.builder().build();
        switch (accountType) {
            case EMAIL:
                userSessionBo = registerForEmail(((EmailRegisterVo) abstractRegisterVo).getEmail(), abstractRegisterVo.getPassword());
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

    private UserSessionBo registerForUsername(String username, String password) {
        // 检查唯一性
        MAssert.isTrue(!cellUserAccountHelper.isUsernameUnique(username), Constants.UserBuzCode.DUPLICATE_ACCOUNT);

        // 创建账户
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(AccountType.USERNAME);
        userAccount.setStatus(UserStatus.NORMAL);
        userAccount.setUsername(username);
        userAccount.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(11)));
        userAccount = cellUserAccountHelper.saveUserAccount(userAccount);
        return UserSessionBo.builder()
                .userAccount(userAccount)
                .build();
    }

    private UserSessionBo registerForEmail(String email, String password) {
        // 检查唯一性
        MAssert.isTrue(!cellUserAccountHelper.isUsernameUnique(email), Constants.UserBuzCode.DUPLICATE_ACCOUNT);
        // 创建账户
        UserAccount userAccount = new UserAccount();
        userAccount.setAccountType(AccountType.EMAIL);
        userAccount.setStatus(UserStatus.NORMAL);
        userAccount.setUsername(email);
        userAccount.setPassword(BCrypt.hashpw(password, BCrypt.gensalt(11)));
        userAccount = cellUserAccountHelper.saveUserAccount(userAccount);

        // TODO send email

        return UserSessionBo.builder()
                .userAccount(userAccount)
                .build();
    }


    private UserSessionBo registerForMiniWeChat(String code) {
        return null;
    }

    @Override
    public UserSessionBo login(AccountType accountType, AbstractRegisterVo abstractRegisterVo, int expire) {
        // 校验参数
        ValidationUtil.validate(abstractRegisterVo);

        switch (accountType) {
            case EMAIL:
                EmailRegisterVo emailRegisterVo = (EmailRegisterVo) abstractRegisterVo;
                return loginByUsername(emailRegisterVo.getEmail(), emailRegisterVo.getPassword(), expire);
            case USERNAME:
                UserNameRegisterVo userNameRegisterVo = (UserNameRegisterVo) abstractRegisterVo;
                return loginByUsername(userNameRegisterVo.getUsername(), userNameRegisterVo.getPassword(), expire);
            case MOBILE:
                MobileRegisterVo mobileRegisterVo = (MobileRegisterVo) abstractRegisterVo;
                return loginByMobile(mobileRegisterVo.getMobile(), mobileRegisterVo.getCaptcha(), expire);
            default:
                MAssert.isTrue(false, ResEnum.INTERNAL_ERROR);
        }
        return null;
    }

    private UserSessionBo loginByUsername(String username, String password, int expire) {
        Optional<UserAccount> userAccount = cellUserAccountHelper.getUserAccountByUserName(username);
        MAssert.isTrue(userAccount.isPresent(), Constants.UserBuzCode.ACCOUNT_MISMATCH);
        MAssert.isTrue(BCrypt.checkpw(password,
                userAccount.get().getPassword()), Constants.UserBuzCode.ACCOUNT_MISMATCH);
        UserToken userToken = cellUserTokenService.genUserToken(userAccount.get().getId(), expire);
        return UserSessionBo.builder()
                .userAccount(userAccount.get())
                .userToken(userToken)
                .build();
    }

    /**
     * 手机号登录或注册
     *
     * @param mobile
     * @param captcha
     * @return
     */
    private UserSessionBo loginByMobile(String mobile, String captcha, int expire) {
        MAssert.isTrue(StringUtils.isNotBlank(mobile), Constants.UserBuzCode.EMPTY_MOBILE);
        MAssert.isTrue(StringUtils.isNotBlank(captcha), Constants.UserBuzCode.EMPTY_CAPTCHA);
        // 校验验证码
        String key = getCaptchaKey(mobile, captcha);
        MAssert.isTrue(redisUtil.getLong(key) != null, Constants.UserBuzCode.CAPTCHA_MISMATCH);

        UserAccount userAccount = null;
        Optional<UserAccount> userAccountOpt = cellUserAccountHelper.getUserAccountByUserName(mobile);
        if (userAccountOpt.isPresent()) {
            // 登录
            userAccount = userAccountOpt.get();
        } else {
            // 注册
            userAccount = new UserAccount();
            userAccount.setAccountType(AccountType.MOBILE);
            userAccount.setStatus(UserStatus.NORMAL);
            userAccount.setUsername(mobile);
            userAccount = cellUserAccountHelper.saveUserAccount(userAccount);

        }
        // 删除验证码
        redisUtil.delete(key);
        return UserSessionBo.builder()
                .userAccount(userAccount)
                .userToken(cellUserTokenService.genUserToken(userAccount.getId(), expire))
                .build();
    }


    @Override
    public void sendCaptcha(String mobile) {
        MAssert.isTrue(StringUtils.isNotBlank(mobile), Constants.UserBuzCode.EMPTY_MOBILE);
        // 生成验证码
        String code = CaptchaUtil.genNumberCode(4);
        String key = getCaptchaKey(mobile, code);
        // 过期时间 1 min
        redisUtil.setLong(key, 1L, DateUtil.SECONDS_PER_MINUTE);
        // TODO send 验证码 短信
    }

    private String getCaptchaKey(String mobile, String captcha) {
        return String.format("%s:%s", mobile, captcha);
    }

}
