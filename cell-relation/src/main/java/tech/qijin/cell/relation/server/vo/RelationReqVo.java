package tech.qijin.cell.relation.server.vo;

import lombok.Data;
import tech.qijin.cell.relation.base.RelationKind;

@Data
public class RelationReqVo {
    private Long id;
    private Long peerUserId;
    private RelationKind kind;
}
