package tech.qijin.cell.account.spi.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.base.StatementSrc;

@Data
@Builder
public class StatementSrcBo {
    private StatementSrc src;
    private String name;

    public static StatementSrcBo defaultBo(StatementSrc src) {
        return StatementSrcBo.builder()
                .src(src)
                .name("Unknown")
                .build();
    }
}
