package tech.qijin.cell.relation.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RelationsVo {
    private List<RelationVo> relations;
    private Long count;
}
