package tech.qijin.cell.relation.helper.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.base.RelationStatus;
import tech.qijin.cell.relation.db.dao.RelationDao;
import tech.qijin.cell.relation.db.model.Relation;
import tech.qijin.cell.relation.db.model.RelationExample;
import tech.qijin.cell.relation.helper.CellRelationHelper;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@Slf4j
@Service
public class CellRelationHelperImpl implements CellRelationHelper {
    @Autowired
    private RelationDao relationDao;

    @Override
    public Relation getRelation(Long userId, Long peerUserId, RelationKind kind) {
        RelationExample example = new RelationExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andPeerUserIdEqualTo(peerUserId)
                .andKindEqualTo(kind);
        return relationDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public boolean updateRelationStatus(Relation relation, RelationStatus toStatus) {
        if (!relation.getStatus().isFlowableTo(toStatus)) {
            log.warn("updateRelationStatus fail, unsupported transition, fromStatus={}, toStatus={}", relation.getStatus(), toStatus);
            return false;
        }
        Relation update = new Relation();
        update.setStatus(toStatus);
        update.setVersion(relation.getVersion() + 1);

        RelationExample example = new RelationExample();
        example.createCriteria()
                .andIdEqualTo(relation.getId())
                .andVersionEqualTo(relation.getVersion());
        return relationDao.updateByExampleSelective(update, example) > 0;
    }

    @Override
    public boolean insertRelation(Relation relation) {
        String res = checkRelation(relation);
        if (StringUtils.isNotBlank(res)) {
            log.error("insertRelation fail, res={}, relation={}", res, JSON.toJSONString(relation));
            return false;
        }
        relation.setStatus(RelationStatus.NORMAL);
        return relationDao.insertSelective(relation) > 0;
    }

    @Override
    public boolean updateRelationTime(Relation relation) {
        Relation update = new Relation();
        update.setRelationTime(DateUtil.now());
        update.setVersion(relation.getVersion() + 1);

        RelationExample example = new RelationExample();
        example.createCriteria()
                .andIdEqualTo(relation.getId())
                .andVersionEqualTo(relation.getVersion());
        return relationDao.updateByExampleSelective(update, example) > 0;
    }

    @Override
    public List<Relation> pageRelation(Long userId, RelationKind kind, Integer pageNo, Integer pageSize) {
        RelationExample example = new RelationExample();
        example.setOrderByClause(String.format("relation_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindEqualTo(kind)
                .andStatusEqualTo(RelationStatus.NORMAL);
        return relationDao.selectByExample(example);
    }

    @Override
    public Long countRelation(Long userId, RelationKind kind) {
        RelationExample example = new RelationExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andKindEqualTo(kind)
                .andStatusEqualTo(RelationStatus.NORMAL);
        return relationDao.countByExample(example);
    }

    private String checkRelation(Relation relation) {
        if (relation == null) return "entity";
        if (relation.getKind() == null) return "kind";
        if (!NumberUtil.gtZero(relation.getUserId())) return "userId";
        if (!NumberUtil.gtZero(relation.getPeerUserId())) return "peerUserId";
        if (StringUtils.isBlank(relation.getFormat())) return "format";
        return "";
    }
}
