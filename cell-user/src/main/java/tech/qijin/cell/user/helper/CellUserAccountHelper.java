package tech.qijin.cell.user.helper;

import tech.qijin.cell.user.db.model.UserAccount;

import java.util.Optional;

public interface CellUserAccountHelper {
    UserAccount saveUserAccount(UserAccount userAccount);

    Optional<UserAccount> getUserAccountByUserName(String userName);

    /**
     * 检查 username是否是唯一的
     * @param username
     */
    boolean isUsernameUnique(String username);
}
