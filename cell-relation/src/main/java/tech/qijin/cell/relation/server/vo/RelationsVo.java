package tech.qijin.cell.relation.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RelationsVo {
    private RelationVo relation;
    private List<RelationVo> relations;
    // 关系总数
    private Long count;
    // 未读数总数
    private Long unreadCount;
}
