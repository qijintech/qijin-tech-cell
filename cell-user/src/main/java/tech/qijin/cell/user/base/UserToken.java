package tech.qijin.cell.user.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserToken {
    private String token;
    private Long expire;
}
