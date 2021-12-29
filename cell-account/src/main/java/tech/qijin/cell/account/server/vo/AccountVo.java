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

    public static AccountVo from(Account account) {
        if (account == null) return null;
        return AccountVo.builder()
                .kind(account.getKind())
                .balance(account.getBalance())
                .build();
    }
}
