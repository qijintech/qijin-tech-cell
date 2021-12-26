package tech.qijin.cell.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.helper.CellAccountHelper;
import tech.qijin.cell.account.service.CellAccountService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@Slf4j
@Service
public class CellAccountServiceImpl implements CellAccountService {
    @Autowired
    private CellAccountHelper cellAccountHelper;

    @Override
    public Account getAccount(Long userId, AccountKind kind) {
        return null;
    }

    @Override
    public List<Account> listAccount(Long userId) {
        return null;
    }

    @Retryable(value = {Exception.class}, backoff = @Backoff(delay = 10L, multiplier = 2.0))
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateAccount(Long userId,
                                 AccountKind kind,
                                 Long amount,
                                 StatementSrc src,
                                 Long dataId) {
        if (!NumberUtil.gtZero(amount)) return false;

        check(src, amount);

        if (!cellAccountHelper.insertAccountStatement(userId, kind, amount, src, dataId)) {
            log.warn("updateAccount duplicate, userId={}, kind={}, amount={}, src={}, dataId={}",
                    userId, kind, amount, src, dataId);
            return false;
        }
        Account account = cellAccountHelper.getAccount(userId, kind);
        if (account == null) {
            account = new Account();
            account.setUserId(userId);
            account.setBalance(amount);
            account.setKind(kind);
            return cellAccountHelper.insertAccount(account);
        } else {
            return cellAccountHelper.updateAccount(account, amount);
        }
    }

    private void check(StatementSrc src, Long amount) {
        if (src.isNegative()) {
            MAssert.isTrue(amount < 0, ResEnum.INVALID_PARAM);
        } else {
            MAssert.isTrue(amount > 0, ResEnum.INVALID_PARAM);
        }
    }
}
