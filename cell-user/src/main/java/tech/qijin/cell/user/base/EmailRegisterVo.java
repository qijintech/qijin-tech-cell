package tech.qijin.cell.user.base;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class EmailRegisterVo extends AbstractRegisterVo {
    @Email
    @NotNull(message = "邮箱不能为空")
    private String email;
}
