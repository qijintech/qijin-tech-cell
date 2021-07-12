package tech.qijin.cell.user.helper;

import tech.qijin.cell.user.db.model.UserAccount;
import tech.qijin.cell.user.db.model.UserAccountMini;

import java.util.Optional;

public interface CellUserAccountHelper {
    UserAccount createUserAccount(UserAccount userAccount);
    UserAccount getUserAccount(Long userId);

    Optional<UserAccount> getUserAccountByUserName(String userName);

    /**
     * 检查 username是否是唯一的
     *
     * @param username
     */
    boolean isUsernameUnique(String username);

    /**
     * 检查openid是否存在
     *
     * @param openid
     * @return
     */
    boolean isOpenidExist(String openid);

    UserAccountMini createUserAccountMini(String openid, String sessionKey, Long userId);

    UserAccountMini getUserAccountMini(String openid);

}
