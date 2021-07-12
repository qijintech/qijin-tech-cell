package tech.qijin.cell.user.base;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class WechatRegisterVo extends AbstractRegisterVo{
    @NonNull
    private String code;
}
