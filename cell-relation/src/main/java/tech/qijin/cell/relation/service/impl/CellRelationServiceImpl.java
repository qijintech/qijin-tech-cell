package tech.qijin.cell.relation.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.relation.base.CacheKey;
import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.base.RelationStatus;
import tech.qijin.cell.relation.bo.OpRelationBo;
import tech.qijin.cell.relation.db.model.Relation;
import tech.qijin.cell.relation.helper.CellRelationHelper;
import tech.qijin.cell.relation.service.CellRelationService;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class CellRelationServiceImpl implements CellRelationService {
    private Integer defaultPageSize = 10;
    @Autowired
    private CellRelationHelper cellRelationHelper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public OpRelationBo addRelation(Long userId, Long peerUserId, RelationKind kind) {
        Long dailyAddCount = getDailyAddCount(userId, kind);
        OpRelationBo opRelationBo = OpRelationBo.of(dailyAddCount);
        if (userId.equals(peerUserId)) return opRelationBo;
        Relation existedRelation = cellRelationHelper.getRelation(userId, peerUserId, kind);
        if (existedRelation == null) {
            Relation relation = new Relation();
            relation.setUserId(userId);
            relation.setPeerUserId(peerUserId);
            relation.setKind(kind);
            relation.setFormat(format(userId, peerUserId, kind));
            if (cellRelationHelper.insertRelation(relation)) {
                incrUnread(userId, kind);
                return OpRelationBo.builder()
                        .opDailyCount(incrDailyAddCount(userId, kind))
                        .build();
            }
            return opRelationBo;
        }
        if (RelationStatus.NORMAL.equals(existedRelation.getStatus())) {
            return opRelationBo;
        }
        if (RelationStatus.DELETED.equals(existedRelation.getStatus())) {
            if (cellRelationHelper.updateRelationStatus(existedRelation, RelationStatus.NORMAL)) {
                incrUnread(userId, kind);
                return OpRelationBo.builder()
                        .opDailyCount(incrDailyAddCount(userId, kind))
                        .build();
            }
            return opRelationBo;
        }
        return opRelationBo;
    }

    @Override
    public OpRelationBo removeRelation(Long userId, Long peerUserId, RelationKind kind) {
        Long dailyRemoveCount = getDailyRemoveCount(userId, kind);
        OpRelationBo opRelationBo = OpRelationBo.of(dailyRemoveCount);
        if (userId.equals(peerUserId)) return opRelationBo;
        Relation existedRelation = cellRelationHelper.getRelation(userId, peerUserId, kind);
        if (existedRelation == null) {
            return opRelationBo;
        }
        if (RelationStatus.DELETED.equals(existedRelation.getStatus())) {
            return opRelationBo;
        }
        if (RelationStatus.NORMAL.equals(existedRelation.getStatus())) {
            if (cellRelationHelper.updateRelationStatus(existedRelation, RelationStatus.DELETED)) {
                decrUnread(userId, kind);
                return OpRelationBo.of(incrDailyRemoveCount(userId, kind));
            }
            return opRelationBo;
        }
        return opRelationBo;
    }

    @Override
    public boolean hasRelation(Long userId, Long peerUserId, RelationKind kind) {
        if (userId.equals(peerUserId)) return false;
        Relation existedRelation = cellRelationHelper.getRelation(userId, peerUserId, kind);
        if (existedRelation == null) return false;
        return RelationStatus.NORMAL.equals(existedRelation.getStatus());
    }

    @Override
    public List<Relation> pageRelation(Long userId, RelationKind kind, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        if (pageVo.isFirstPage()) {
            clearUnread(userId, kind);
        }
        return cellRelationHelper.pageRelation(userId, kind, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public Relation lastedRelation(Long userId, RelationKind kind, Integer n) {
        return cellRelationHelper.pageRelation(userId, kind, 1, n).stream().findFirst().orElse(null);
    }

    @Override
    public Long countRelation(Long userId, RelationKind kind) {
        return cellRelationHelper.countRelation(userId, kind);
    }

    @Override
    public Long countUnread(Long userId, RelationKind kind) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId, kind);
        Long unread = redisUtil.getLong(key);
        return NumberUtil.gtZero(unread) ? unread : 0L;
    }

    @Override
    public void incrUnread(Long userId, RelationKind kind) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId, kind);
        redisUtil.increment(key, 1L);
        redisUtil.setExpire(key, 100, TimeUnit.DAYS);
    }

    @Override
    public void decrUnread(Long userId, RelationKind kind) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId, kind);
        redisUtil.decrement(key, 1L);
        redisUtil.setExpire(key, 100, TimeUnit.DAYS);
    }

    @Override
    public void clearUnread(Long userId, RelationKind kind) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId, kind);
        redisUtil.delete(key);
    }

    private Long getDailyAddCount(Long userId, RelationKind kind) {
        return redisUtil.getLong(CacheKey.INSTANCE.dailyAddCountKey(userId, kind));
    }
    private long incrDailyAddCount(Long userId, RelationKind kind) {
        String key = CacheKey.INSTANCE.dailyAddCountKey(userId, kind);
        return redisUtil.increment(key, 1);
    }

    private Long getDailyRemoveCount(Long userId, RelationKind kind) {
        return redisUtil.getLong(CacheKey.INSTANCE.dailyMinusCountKey(userId, kind));
    }
    private long incrDailyRemoveCount(Long userId, RelationKind kind) {
        String key = CacheKey.INSTANCE.dailyMinusCountKey(userId, kind);
        return redisUtil.increment(key, 1);
    }

    private String format(Long userId, Long peerUserId, RelationKind kind) {
        return String.format("%d:%d:%s", userId, peerUserId, kind.name());
    }

    protected PageVo checkPage(PageVo pageVo, Integer customDefaultPageSize) {
        Integer pageSize = customDefaultPageSize == null ? defaultPageSize : customDefaultPageSize;
        if (pageVo == null) {
            return new PageVo(1, pageSize);
        }
        if (!NumberUtil.gtZero(pageVo.getPageNo())) {
            pageVo.setPageNo(1);
        }
        if (!NumberUtil.gtZero(pageVo.getPageSize())) {
            pageVo.setPageSize(pageSize);
        }
        return pageVo;
    }
}
