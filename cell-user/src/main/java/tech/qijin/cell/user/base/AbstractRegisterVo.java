package tech.qijin.cell.user.base;

import lombok.Data;
import tech.qijin.util4j.web.common.Constants;
import tech.qijin.util4j.web.validation.annotation.Password;

@Data
public abstract class AbstractRegisterVo {
    @Password(strength = Constants.PasswdStrength.ANY)
    private String password;
}
