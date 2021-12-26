package tech.qijin.cell.account.service;

import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Account;

import java.util.List;

public interface CellAccountService {
    /**
     * 获取指定账户信息
     * @param userId
     * @param kind
     * @return
     */
    Account getAccount(Long userId, AccountKind kind);

    /**
     * 获取所有账户信息
     * @param userId
     * @return
     */
    List<Account> listAccount(Long userId);

    /**
     * 更新账户
     * @param userId
     * @param kind
     * @param amount
     * @param src
     * @param dataId
     * @return
     */
    boolean updateAccount(Long userId, AccountKind kind, Long amount, StatementSrc src, Long dataId);
}
