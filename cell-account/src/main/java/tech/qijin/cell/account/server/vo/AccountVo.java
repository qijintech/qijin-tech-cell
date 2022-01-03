package tech.qijin.cell.account.server.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.db.model.Account;

@Data
@Builder
public class AccountVo {

    private AccountKind kind;

    private Long balance;

    private boolean hasRedPoint;

    public static AccountVo from(Account account) {
        return from(account, false);
    }

    public static AccountVo from(Account account, boolean hasRedPoint) {
        if (account == null) return null;
        return AccountVo.builder()
                .kind(account.getKind())
                .balance(account.getBalance())
                .hasRedPoint(hasRedPoint)
                .build();
    }
}
