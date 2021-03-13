package tech.qijin.cell.user.base;

import lombok.Data;
import tech.qijin.util4j.web.validation.annotation.Mobile;

import javax.validation.constraints.NotNull;

@Data
public class MobileRegisterVo extends AbstractRegisterVo{
    @Mobile(message = "手机号不能为空")
    private String mobile;
    @NotNull(message = "验证码不能为空")
    private String captcha;
}
