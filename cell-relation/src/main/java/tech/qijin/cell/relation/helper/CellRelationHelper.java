package tech.qijin.cell.relation.helper;

import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.base.RelationStatus;
import tech.qijin.cell.relation.db.model.Relation;

import java.util.List;

public interface CellRelationHelper {
    /**
     * 获取 relation
     *
     * @param userId
     * @param peerUserId
     * @param kind
     * @return
     */
    Relation getRelation(Long userId, Long peerUserId, RelationKind kind);

    /**
     * 更新 relation status
     *
     * @param relation
     * @param toStatus
     * @return
     */
    boolean updateRelationStatus(Relation relation, RelationStatus toStatus);

    /**
     * 添加relation
     *
     * @param relation
     * @return
     */
    boolean insertRelation(Relation relation);

    /**
     * 只更新 relation_time，影响排序
     *
     * @param relation
     * @return
     */
    boolean updateRelationTime(Relation relation);

    /**
     * 分页查询relation
     *
     * @param userId
     * @param kind
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Relation> pageRelation(Long userId, RelationKind kind, Integer pageNo, Integer pageSize);

    /**
     * 计算数量
     *
     * @param userId
     * @param kind
     * @return
     */
    Long countRelation(Long userId, RelationKind kind);

}
