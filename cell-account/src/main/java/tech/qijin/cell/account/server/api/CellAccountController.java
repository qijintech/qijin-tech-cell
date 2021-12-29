package tech.qijin.cell.account.server.api;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.Statements;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.db.model.Account;
import tech.qijin.cell.account.db.model.AccountStatement;
import tech.qijin.cell.account.server.vo.AccountVo;
import tech.qijin.cell.account.server.vo.StatementVo;
import tech.qijin.cell.account.server.vo.StatementsVo;
import tech.qijin.cell.account.service.CellAccountService;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/account/")
public class CellAccountController {
    @Autowired
    private CellAccountService cellAccountService;

    @RequestMapping("/balance")
    public Map<AccountKind, AccountVo> accountInfo(@RequestParam String kinds) {
        List<AccountKind> kindList = parseAccountKinds(kinds);
        Map<AccountKind, Account> accountMap = cellAccountService.mapAccount(UserUtil.getUserId(), kindList);
        return accountMap.entrySet()
                .stream()
                .collect(Collectors.toMap(entry -> entry.getKey(), entry -> AccountVo.from(entry.getValue())));
    }

    @RequestMapping("/statement")
    public StatementsVo statement(String kinds, PageVo pageVo) {
        List<AccountKind> kindList = parseAccountKinds(kinds);
        List<AccountStatement> statements;
        if (CollectionUtils.isEmpty(kindList)) {
            statements = cellAccountService.pageStatement(UserUtil.getUserId(), pageVo);
        } else if (kindList.size() == 1) {
            statements = cellAccountService.pageStatement(UserUtil.getUserId(), kindList.get(0), pageVo);
        } else {
            statements = cellAccountService.pageStatement(UserUtil.getUserId(), kindList, pageVo);
        }
        return StatementsVo.builder()
                .statements(StatementVo.from(statements))
                .build();
    }

    private List<AccountKind> parseAccountKinds(String kinds) {
        Set<AccountKind> kindSet = Arrays.stream(kinds.split(","))
                .map(kind -> AccountKind.from(StringUtils.trim(kind)))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
        return Lists.newArrayList(kindSet);
    }
}
