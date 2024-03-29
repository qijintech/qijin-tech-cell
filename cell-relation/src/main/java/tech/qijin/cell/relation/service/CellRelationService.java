package tech.qijin.cell.relation.service;

import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.bo.OpRelationBo;
import tech.qijin.cell.relation.db.model.Relation;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;

public interface CellRelationService {
    /**
     * 添加relation
     *
     * @param userId
     * @param peerUserId
     * @param kind
     * @return
     */
    OpRelationBo addRelation(Long userId, Long peerUserId, RelationKind kind);

    OpRelationBo removeRelation(Long userId, Long peerUserId, RelationKind kind);

    /**
     * 判断两个人是否有某种关系
     *
     * @param userId
     * @param peerUserId
     * @param kind
     * @return
     */
    boolean hasRelation(Long userId, Long peerUserId, RelationKind kind);

    /**
     * 查询关系
     *
     * @param userId
     * @param kind
     * @return
     */
    List<Relation> pageRelation(Long userId, RelationKind kind, PageVo pageVo);

    /**
     * 最近的n个人
     *
     * @param userId
     * @param kind
     * @return
     */
    Relation lastedRelation(Long userId, RelationKind kind, Integer n);

    /**
     * 计算数量
     *
     * @param userId
     * @param kind
     * @return
     */
    Long countRelation(Long userId, RelationKind kind);


    /**
     * 获取未读数
     *
     * @param userId
     * @param kind
     * @return
     */
    Long countUnread(Long userId, RelationKind kind);

    /**
     * 增加未读数
     *
     * @param userId
     * @param kind
     */
    void incrUnread(Long userId, RelationKind kind);

    void decrUnread(Long userId, RelationKind kind);

    /**
     * 减少未读数
     *
     * @param userId
     * @param kind
     */
    void clearUnread(Long userId, RelationKind kind);
}
