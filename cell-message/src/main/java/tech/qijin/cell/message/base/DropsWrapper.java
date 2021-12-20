package tech.qijin.cell.message.base;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DropsWrapper {
    private Long dropsId;
    private String statementSrc;
    private Long dataId;
}
