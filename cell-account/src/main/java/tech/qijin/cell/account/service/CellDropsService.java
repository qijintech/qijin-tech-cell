package tech.qijin.cell.account.service;

import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Drops;
import tech.qijin.cell.account.service.bo.DropsBo;

/**
 * 掉落 service
 */
public interface CellDropsService {
    /**
     * 领取奖励
     *
     * @param userId
     * @param dropsId
     * @param src
     * @param dataId
     */
    DropsBo grantDropsToUser(Long userId, Long dropsId, StatementSrc src, Long dataId, Long dataShowId);

    /**
     * 查看掉落详情
     *
     * @param dropsId
     * @return
     */
    DropsBo getDropsDetail(Long dropsId);

    /**
     * 把DropsBo里面相同的item进行merge
     *
     * @param dropsBo
     * @return
     */
    DropsBo merge(DropsBo dropsBo);
}
