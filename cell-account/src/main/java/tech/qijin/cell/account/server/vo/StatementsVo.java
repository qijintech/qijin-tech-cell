package tech.qijin.cell.account.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StatementsVo {
    private List<StatementVo> statements;
}
