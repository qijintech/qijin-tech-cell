package tech.qijin.cell.user.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.cell.user.base.EmailRegisterVo;
import tech.qijin.cell.user.base.AccountType;
import tech.qijin.cell.user.base.UserSessionBo;
import tech.qijin.cell.user.service.CellUserAccountService;
import tech.qijin.cell.user.service.CellUserTokenService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.exception.ValidateException;
import tech.qijin.util4j.utils.DateUtil;

import java.util.UUID;

public class CellUserTokenServiceTest extends BaseTest{
    @Autowired
    private CellUserTokenService cellUserTokenService;
    @Autowired
    private CellUserAccountService userAccountService;

    @Test
    public void testAuth() {
        EmailRegisterVo emailRegisterVo = new EmailRegisterVo();
        emailRegisterVo.setEmail(USER_NAME);
        emailRegisterVo.setPassword(PASSWORD);
        UserSessionBo userSessionBo = userAccountService.login(AccountType.EMAIL, emailRegisterVo, 7 * DateUtil.SECONDS_PER_DAY);
        Assert.assertNotNull(userSessionBo);
        Assert.assertNotNull(userSessionBo.getUserToken());
        Assert.assertNotNull(userSessionBo.getUserAccount());
        Long userId = cellUserTokenService.auth(userSessionBo.getUserToken().getToken());
        Assert.assertNotNull(userId);
        Assert.assertEquals(userId, userSessionBo.getUserAccount().getId());
    }

    @Test(expected = ValidateException.class)
    public void testAuth_Unauthorized() {
        try {
            Long userId = cellUserTokenService.auth(UUID.randomUUID().toString());
        } catch (Exception e) {
            if (e instanceof ValidateException) {
                Assert.assertEquals(((ValidateException) e).getCode(), ResEnum.UNAUTHORIZED.code);
            }
            throw e;
        }
    }
}
