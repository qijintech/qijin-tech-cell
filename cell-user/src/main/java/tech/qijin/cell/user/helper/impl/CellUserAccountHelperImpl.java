package tech.qijin.cell.user.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.Constants;
import tech.qijin.cell.user.base.UserStatus;
import tech.qijin.cell.user.db.dao.UserAccountDao;
import tech.qijin.cell.user.db.dao.UserAccountMiniDao;
import tech.qijin.cell.user.db.dao.UserAccountUsernameDao;
import tech.qijin.cell.user.db.model.UserAccount;
import tech.qijin.cell.user.db.model.UserAccountExample;
import tech.qijin.cell.user.db.model.UserAccountMini;
import tech.qijin.cell.user.db.model.UserAccountMiniExample;
import tech.qijin.cell.user.helper.CellUserAccountHelper;
import tech.qijin.util4j.trace.util.ChannelUtil;
import tech.qijin.util4j.utils.MAssert;

import java.util.Optional;

@Slf4j
@Service
public class CellUserAccountHelperImpl implements CellUserAccountHelper {
    @Autowired
    private UserAccountUsernameDao userAccountUsernameDao;
    @Autowired
    private UserAccountMiniDao userAccountMiniDao;
    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public UserAccount createUserAccount(UserAccount userAccount) {
        try {
            userAccountDao.insertSelective(userAccount);
        } catch (DuplicateKeyException e) {
            MAssert.isTrue(false, Constants.UserBuzCode.DUPLICATE_ACCOUNT);
        }
        return userAccount;
    }

    @Override
    public UserAccount getUserAccount(Long userId) {
        return userAccountDao.selectByPrimaryKey(userId);
    }

    @Override
    public Optional<UserAccount> getUserAccountByUserName(String username) {
//        UserAccountExample example = new UserAccountExample();
//        example.createCriteria()
//                .andUsernameEqualTo(username);
//        return userAccountUsernameDao.selectByExample(example).stream().findFirst();
        return null;
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return !getUserAccountByUserName(username).isPresent();
    }

    @Override
    public boolean isOpenidExist(String openid) {
        UserAccountMini userAccountMini = getUserAccountMini(openid);
        return userAccountMini != null;
    }

    @Override
    public UserAccountMini createUserAccountMini(String openid, String sessionKey, Long userId) {
        UserAccountMini userAccountMini = new UserAccountMini();
        userAccountMini.setChannel(ChannelUtil.getChannel());
        userAccountMini.setOpenid(openid);
        userAccountMini.setSessionKey(sessionKey);
        userAccountMini.setStatus(UserStatus.NORMAL);
        userAccountMini.setUserId(userId);
        userAccountMiniDao.insertSelective(userAccountMini);
        return userAccountMini;
    }

    @Override
    public UserAccountMini getUserAccountMini(String openid) {
        UserAccountMiniExample example = new UserAccountMiniExample();
        example.createCriteria().andOpenidEqualTo(openid);
        return userAccountMiniDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public UserAccountMini getUserAccountMini(Long userId) {
        UserAccountMiniExample example = new UserAccountMiniExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return userAccountMiniDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public boolean updateSessionKey(String openid, String sessionKey) {
        UserAccountMini record = new UserAccountMini();
        record.setSessionKey(sessionKey);

        UserAccountMiniExample example = new UserAccountMiniExample();
        example.createCriteria().andOpenidEqualTo(openid);
        return userAccountMiniDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public String getOpenid(Long userId) {
        UserAccountMiniExample example = new UserAccountMiniExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return userAccountMiniDao.selectByExample(example).stream()
                .map(UserAccountMini::getOpenid)
                .findFirst()
                .orElse("");
    }
}
