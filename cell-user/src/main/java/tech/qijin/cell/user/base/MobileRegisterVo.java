package tech.qijin.cell.user.base;

import lombok.Data;
import tech.qijin.util4j.web.validation.annotation.Mobile;

@Data
public class MobileRegisterVo extends AbstractRegisterVo{
    @Mobile
    private String mobile;
}
