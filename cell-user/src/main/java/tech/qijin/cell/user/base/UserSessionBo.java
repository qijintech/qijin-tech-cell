package tech.qijin.cell.user.base;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tech.qijin.cell.user.db.model.UserAccount;

@Data
@Builder
public class UserSessionBo {
    private UserAccount userAccount;
    private UserToken userToken;
    private LoginStatus loginStatus;
}
