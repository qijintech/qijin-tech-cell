package tech.qijin.cell.account.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.CacheKey;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.db.model.AccountStatement;
import tech.qijin.cell.account.helper.CellAccountHelper;
import tech.qijin.cell.account.service.CellAccountService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CellAccountServiceImpl implements CellAccountService {
    private Integer defaultPageSize = 10;

    @Autowired
    private CellAccountHelper cellAccountHelper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public Account getAccount(Long userId, AccountKind kind) {
        return cellAccountHelper.getAccount(userId, kind);
    }

    @Override
    public Map<AccountKind, Account> mapAccount(Long userId, List<AccountKind> kinds) {
        return cellAccountHelper.mapAccountByKinds(userId, kinds);
    }

    @Override
    public List<Account> listAccount(Long userId) {
        return cellAccountHelper.listAccount(userId);
    }

    @Override
    public List<AccountStatement> pageStatement(Long userId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        Arrays.stream(AccountKind.values()).forEach(kind -> clearRedPoint(userId, kind));
        return cellAccountHelper.pageStatement(userId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public List<AccountStatement> pageStatement(Long userId, AccountKind kind, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        if (pageVo.isFirstPage()) {
            clearRedPoint(userId, kind);
        }
        return cellAccountHelper.pageStatement(userId, kind, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public List<AccountStatement> pageStatement(Long userId, List<AccountKind> kinds, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        kinds.stream().forEach(kind -> clearRedPoint(userId, kind));
        return cellAccountHelper.pageStatement(userId, kinds, pageVo.getPageNo(), pageVo.getPageSize());
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

        // 增加小红点
        addRedPoint(userId, kind);

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

    @Override
    public void addRedPoint(Long userId, AccountKind kind) {
        String key = CacheKey.INSTANCE.redPointKey(userId, kind);
        redisUtil.setString(key, "Y");
    }

    @Override
    public void clearRedPoint(Long userId, AccountKind kind) {
        String key = CacheKey.INSTANCE.redPointKey(userId, kind);
        redisUtil.delete(key);
    }

    @Override
    public boolean hasRedPoint(Long userId, AccountKind kind) {
        String key = CacheKey.INSTANCE.redPointKey(userId, kind);
        return StringUtils.isNotBlank(redisUtil.getString(key));
    }

    private void check(StatementSrc src, Long amount) {
        if (src.isNegative()) {
            MAssert.isTrue(amount < 0, ResEnum.INVALID_PARAM);
        } else {
            MAssert.isTrue(amount > 0, ResEnum.INVALID_PARAM);
        }
    }

    protected PageVo checkPage(PageVo pageVo, Integer customDefaultPageSize) {
        Integer pageSize = customDefaultPageSize == null ? defaultPageSize : customDefaultPageSize;
        if (pageVo == null) {
            return new PageVo(1, pageSize);
        }
        if (!NumberUtil.gtZero(pageVo.getPageNo())) {
            pageVo.setPageNo(1);
        }
        if (!NumberUtil.gtZero(pageVo.getPageSize())) {
            pageVo.setPageSize(pageSize);
        }
        return pageVo;
    }
}
