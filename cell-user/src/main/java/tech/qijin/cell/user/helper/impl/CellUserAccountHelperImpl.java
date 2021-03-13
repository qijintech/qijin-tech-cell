package tech.qijin.cell.user.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.Constants;
import tech.qijin.cell.user.db.dao.UserAccountDao;
import tech.qijin.cell.user.db.model.UserAccount;
import tech.qijin.cell.user.db.model.UserAccountExample;
import tech.qijin.cell.user.helper.CellUserAccountHelper;
import tech.qijin.util4j.utils.MAssert;

import java.util.Optional;

@Slf4j
@Service
public class CellUserAccountHelperImpl implements CellUserAccountHelper {
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public UserAccount saveUserAccount(UserAccount userAccount) {
        try {
            userAccountDao.insertSelective(userAccount);
        } catch (DuplicateKeyException e) {
            MAssert.isTrue(false, Constants.UserBuzCode.DUPLICATE_ACCOUNT);
        }
        return userAccount;
    }

    @Override
    public Optional<UserAccount> getUserAccountByUserName(String username) {
        UserAccountExample example = new UserAccountExample();
        example.createCriteria()
                .andUsernameEqualTo(username);
        return userAccountDao.selectByExample(example).stream().findFirst();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !getUserAccountByUserName(username).isPresent();
    }
}
