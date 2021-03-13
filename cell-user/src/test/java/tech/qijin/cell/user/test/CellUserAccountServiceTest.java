package tech.qijin.cell.user.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.cell.user.base.AccountType;
import tech.qijin.cell.user.base.Constants;
import tech.qijin.cell.user.base.EmailRegisterVo;
import tech.qijin.cell.user.base.UserSessionBo;
import tech.qijin.cell.user.service.CellUserAccountService;
import tech.qijin.util4j.lang.exception.ValidateException;
import tech.qijin.util4j.utils.DateUtil;


public class CellUserAccountServiceTest extends BaseTest {
    @Autowired
    private CellUserAccountService cellUserAccountService;

    @Test
    public void testRegister() {
        EmailRegisterVo emailRegisterVo = new EmailRegisterVo();
        emailRegisterVo.setEmail(USER_NAME);
        emailRegisterVo.setPassword(PASSWORD);
        UserSessionBo userSessionBo = cellUserAccountService.register(AccountType.EMAIL, emailRegisterVo, false, 0);
        Assert.assertNotNull(userSessionBo);
        Assert.assertNotNull(userSessionBo.getUserAccount());
        Assert.assertTrue(userSessionBo.getUserAccount().getId() > 0);
    }

    @Test
    public void testLogin() {
        EmailRegisterVo emailRegisterVo = new EmailRegisterVo();
        emailRegisterVo.setPassword(PASSWORD);
        emailRegisterVo.setEmail(USER_NAME);
        UserSessionBo userSessionBo = cellUserAccountService.login(AccountType.EMAIL, emailRegisterVo, 7 * DateUtil.SECONDS_PER_DAY);
        Assert.assertNotNull(userSessionBo);
        Assert.assertNotNull(userSessionBo.getUserAccount());
        Assert.assertTrue(userSessionBo.getUserAccount().getId() > 0);
    }

    @Test(expected = ValidateException.class)
    public void testLogin_WrongUser() {
        try {
            EmailRegisterVo emailRegisterVo = new EmailRegisterVo();
            emailRegisterVo.setPassword(PASSWORD);
            emailRegisterVo.setEmail("asdf@aliyun.com");
            UserSessionBo userSessionBo = cellUserAccountService.login(AccountType.EMAIL, emailRegisterVo, 7 * DateUtil.SECONDS_PER_DAY);
            Assert.assertNotNull(userSessionBo);
            Assert.assertNotNull(userSessionBo.getUserAccount());
            Assert.assertTrue(userSessionBo.getUserAccount().getId() > 0);
        } catch (Exception e) {
            if (e instanceof ValidateException) {
                Assert.assertEquals(((ValidateException) e).getCode() , Constants.UserBuzCode.ACCOUNT_MISMATCH.code());
            }
            throw e;
        }
    }

    @Test(expected = ValidateException.class)
    public void testLogin_WrongPassword() {
        try {
            EmailRegisterVo emailRegisterVo = new EmailRegisterVo();
            emailRegisterVo.setPassword(PASSWORD + "1");
            emailRegisterVo.setEmail(USER_NAME);
            UserSessionBo userSessionBo = cellUserAccountService.login(AccountType.EMAIL, emailRegisterVo, 7 * DateUtil.SECONDS_PER_DAY);
            Assert.assertNotNull(userSessionBo);
            Assert.assertNotNull(userSessionBo.getUserAccount());
            Assert.assertTrue(userSessionBo.getUserAccount().getId() > 0);
        } catch (Exception e) {
            if (e instanceof ValidateException) {
                Assert.assertEquals(((ValidateException) e).getCode() , Constants.UserBuzCode.ACCOUNT_MISMATCH.code());
            }
            throw e;
        }
    }
}
