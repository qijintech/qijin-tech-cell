package tech.qijin.cell.account.helper;

import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.db.model.Account;

import java.util.List;
import java.util.Map;

public interface CellAccountHelper {
    /**
     * 添加账户信息
     *
     * @param account
     * @return
     */
    boolean insertAccount(Account account);

    /**
     * 获取指定 {@link AccountKind} 的账户信息
     *
     * @param userId
     * @param kind
     * @return
     */
    Account getAccount(Long userId, AccountKind kind);

    /**
     * 获取用户所有的账户信息
     *
     * @param userId
     * @return
     */
    List<Account> listAccount(Long userId);

    /**
     * 获取多个 {@link AccountKind} 的账户信息
     *
     * @param userId
     * @param kinds
     * @return
     */
    Map<AccountKind, Account> mapAccountByKinds(Long userId, List<AccountKind> kinds);
}
