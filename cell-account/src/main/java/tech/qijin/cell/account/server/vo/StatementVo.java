package tech.qijin.cell.account.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.AccountStatement;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class StatementVo {
    private Long id;

    private AccountKind kind;

    private StatementSrc statementSrc;

    private Long dataId;

    private Long dataShowId;

    private Long amount;

    private Date createTime;

    private String createTimeStr;

    // 流水来源前端展示的名称
    private String statementSrcName;

    public static StatementVo from(AccountStatement statement) {
        StatementVo statementVo = ConvertUtil.convert(statement, StatementVo.class);
        if (statementVo != null) {
            statementVo.setCreateTimeStr(DateUtil.formatStr(statementVo.getCreateTime(), DateUtil.DATETIME_FORMAT));
        }
        return statementVo;
    }

    public static List<StatementVo> from(List<AccountStatement> statements) {
        if (CollectionUtils.isEmpty(statements)) return Collections.emptyList();
        return statements.stream()
                .map(StatementVo::from)
                .collect(Collectors.toList());
    }
}
