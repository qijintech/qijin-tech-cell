package tech.qijin.cell.relation.bo;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OpRelationBo {
    private Long opDailyCount;

    public static OpRelationBo of(Long opDailyCount) {
        return OpRelationBo.builder()
                .opDailyCount(opDailyCount)
                .build();
    }
}
