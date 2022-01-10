package tech.qijin.cell.account.service;

import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.db.model.AccountStatement;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;
import java.util.Map;

public interface CellAccountService {
    /**
     * 获取指定账户信息
     *
     * @param userId
     * @param kind
     * @return
     */
    Account getAccount(Long userId, AccountKind kind);

    Map<AccountKind, Account> mapAccount(Long userId, List<AccountKind> kinds);

    /**
     * 获取所有账户信息
     *
     * @param userId
     * @return
     */
    List<Account> listAccount(Long userId);

    /**
     * 列出流水
     *
     * @param userId
     * @param pageVo
     * @return
     */
    List<AccountStatement> pageStatement(Long userId, PageVo pageVo);

    List<AccountStatement> pageStatement(Long userId, AccountKind kind, PageVo pageVo);

    List<AccountStatement> pageStatement(Long userId, List<AccountKind> kinds, PageVo pageVo);

    /**
     * 更新账户
     *
     * @param userId
     * @param kind
     * @param amount
     * @param src
     * @param dataId
     * @return
     */
    boolean updateAccount(Long userId,
                          AccountKind kind,
                          Long amount,
                          StatementSrc src,
                          Long dataId,
                          Long dataShowId);

    /**
     * 账户更新小红点
     *
     * @param userId
     * @param kind
     */
    void addRedPoint(Long userId, AccountKind kind);

    /**
     * 清除账户小红点
     *
     * @param userId
     * @param kind
     */
    void clearRedPoint(Long userId, AccountKind kind);

    /**
     * 是否还有小红点
     *
     * @param userId
     * @param kind
     * @return
     */
    boolean hasRedPoint(Long userId, AccountKind kind);
}
