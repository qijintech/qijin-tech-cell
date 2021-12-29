package tech.qijin.cell.account.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.dao.AccountDao;
import tech.qijin.cell.account.db.dao.AccountStatementDao;
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.db.model.AccountExample;
import tech.qijin.cell.account.db.model.AccountStatement;
import tech.qijin.cell.account.db.model.AccountStatementExample;
import tech.qijin.cell.account.helper.CellAccountHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellAccountHelperImpl implements CellAccountHelper {
    private String formatSeparator = ":";
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
    public boolean updateAccount(Account account, Long amount) {
        MAssert.isTrue(account != null, ResEnum.BAD_GATEWAY);
        Account update = new Account();
        update.setBalance(account.getBalance() + amount);
        update.setVersion(account.getVersion() + 1);
        AccountExample example = new AccountExample();
        example.createCriteria()
                .andIdEqualTo(account.getId())
                .andVersionEqualTo(account.getVersion());
        return accountDao.updateByExampleSelective(update, example) > 0;
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
        if (CollectionUtils.isEmpty(kinds)) return Collections.emptyMap();
        AccountExample example = new AccountExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindIn(kinds);
        return accountDao.selectByExample(example)
                .stream()
                .collect(Collectors.toMap(Account::getKind, Function.identity()));
    }

    @Override
    public boolean insertAccountStatement(Long userId,
                                          AccountKind kind,
                                          Long amount,
                                          StatementSrc src,
                                          Long dataId) {
        AccountStatement statement = new AccountStatement();
        statement.setUserId(userId);
        statement.setKind(kind);
        statement.setAmount(amount);
        statement.setStatementSrc(src);
        statement.setDataId(dataId);
        statement.setFormat(formatStatement(userId, kind, src, dataId));
        try {
            return accountStatementDao.insertSelective(statement) > 0;
        } catch (DuplicateKeyException e) {
            return false;
        }
    }

    @Override
    public List<AccountStatement> pageStatement(Long userId, Integer pageNo, Integer pageSize) {
        AccountStatementExample example = new AccountStatementExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return accountStatementDao.selectByExample(example);
    }

    @Override
    public List<AccountStatement> pageStatement(Long userId, AccountKind kind, Integer pageNo, Integer pageSize) {
        AccountStatementExample example = new AccountStatementExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindEqualTo(kind);
        return accountStatementDao.selectByExample(example);
    }

    @Override
    public List<AccountStatement> pageStatement(Long userId, List<AccountKind> kinds, Integer pageNo, Integer pageSize) {
        AccountStatementExample example = new AccountStatementExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindIn(kinds);
        return accountStatementDao.selectByExample(example);
    }

    private String formatStatement(Long userId, AccountKind kind, StatementSrc src, Long dataId) {
        StringBuilder sb = new StringBuilder();
        return sb.append(userId)
                .append(formatSeparator)
                .append(kind)
                .append(formatSeparator)
                .append(src)
                .append(formatSeparator)
                .append(dataId)
                .toString();
    }

    private String checkAccount(Account account) {
        if (!NumberUtil.gtZero(account.getUserId())) return "userId";
        if (account.getKind() == null) return "kind";
        return "";
    }
}
