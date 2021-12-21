package tech.qijin.cell.account.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.db.dao.AccountDao;
import tech.qijin.cell.account.db.dao.AccountStatementDao;
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.db.model.AccountExample;
import tech.qijin.cell.account.helper.CellAccountHelper;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CellAccountHelperImpl implements CellAccountHelper {
    @Autowired
    private AccountDao accountDao;
    @Autowired
    private AccountStatementDao accountStatementDao;

    @Override
    public boolean insertAccount(Account account) {
        String res = checkAccount(account);
        if (StringUtils.isNotBlank(res)) {
            log.error("insertAccount invalid param, res={}, account={}", res, account);
            return false;
        }
        return accountDao.insertSelective(account) > 0;
    }

    @Override
    public Account getAccount(Long userId, AccountKind kind) {
        AccountExample example = new AccountExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindEqualTo(kind);
        return accountDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public List<Account> listAccount(Long userId) {
        AccountExample example = new AccountExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return accountDao.selectByExample(example);
    }

    @Override
    public Map<AccountKind, Account> mapAccountByKinds(Long userId, List<AccountKind> kinds) {
        return null;
    }

    private String checkAccount(Account account) {
        if (!NumberUtil.gtZero(account.getUserId())) return "userId";
        if (account.getKind() == null) return "kind";
        return "";
    }
}
