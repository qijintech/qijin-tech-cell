package tech.qijin.cell.account.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.account.base.DropsItemKind;
import tech.qijin.cell.account.base.StatementSrc;
import tech.qijin.cell.account.db.model.Drops;
import tech.qijin.cell.account.db.model.DropsItem;
import tech.qijin.cell.account.db.model.Item;
import tech.qijin.cell.account.helper.CellDropsHelper;
import tech.qijin.cell.account.helper.CellItemHelper;
import tech.qijin.cell.account.service.CellAccountService;
import tech.qijin.cell.account.service.CellDropsService;
import tech.qijin.cell.account.service.bo.DropsBo;
import tech.qijin.cell.account.service.bo.DropsItemBo;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.MAssert;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellDropsServiceImpl implements CellDropsService {
    @Autowired
    private CellDropsHelper cellDropsHelper;
    @Autowired
    private CellAccountService cellAccountService;
    @Autowired
    private CellItemHelper cellItemHelper;

    @Override
    public DropsBo grantDropsToUser(Long userId, Long dropsId, StatementSrc src, Long dataId, Long dataShowId) {
        DropsBo dropsBo = getDropsDetail(dropsId);
        if (dropsBo == null) {
            log.error("grantDropsToUser failed, dropsBo is null, userId={}, dropsId={}", userId, dropsId);
            return null;
        }
        if (dropsBo.isMerged()) {
            dropsBo.getDropsItemBos()
                    .stream()
                    .forEach(dropsItemBo -> {
                        if (!cellAccountService.updateAccount(
                                userId,
                                dropsItemBo.getItem().getKind(),
                                dropsItemBo.getDropsItem().getAmount(),
                                src,
                                dataId,
                                dataShowId
                                )) {
                            log.error("grantDropsToUser failed, userId={}, dropsId={}, src={}, dataId={}, item={}, amount={}",
                                    userId, dropsId, src, dataId, dropsItemBo.getItem().getKind(), dropsItemBo.getDropsItem().getAmount());
                        }
                    });
        }
        return dropsBo;
    }

    @Override
    public DropsBo getDropsDetail(Long dropsId) {
        Set<Long> dropsIdSet = Sets.newHashSet();
        DropsBo dropsBo = getDropsDetail(dropsId, dropsIdSet);
        if (dropsBo == null) return null;
        return merge(dropsBo);
    }

    @Override
    public DropsBo merge(DropsBo dropsBo) {
        MAssert.notNull(dropsBo, ResEnum.INVALID_PARAM);
        MAssert.notNull(dropsBo.getDrops(), ResEnum.INVALID_PARAM);
        MAssert.isTrue(CollectionUtils.isNotEmpty(dropsBo.getDropsItems()), ResEnum.INVALID_PARAM);
        Map<Long, List<DropsItem>> itemMap = dropsBo.getDropsItems()
                .stream()
                .collect(Collectors.groupingBy(DropsItem::getItemId));
        List<DropsItemBo> itemBos = itemMap.entrySet()
                .stream()
                .filter(entry -> CollectionUtils.isNotEmpty(entry.getValue()))
                .map(entry -> {
                    Long itemId = entry.getKey();
                    List<DropsItem> dropsItems = entry.getValue();
                    Long total = dropsItems.stream().mapToLong(DropsItem::getAmount).sum();
                    DropsItem dropsItem = ConvertUtil.convert(dropsItems.get(0), DropsItem.class);
                    dropsItem.setAmount(total);
                    Item item = cellItemHelper.getItem(itemId);
                    return DropsItemBo.builder()
                            .dropsItem(dropsItem)
                            .item(item)
                            .build();
                }).collect(Collectors.toList());
        dropsBo.setDropsItemBos(itemBos);
        dropsBo.setMerged(true);
        return dropsBo;
    }

    private DropsBo getDropsDetail(Long dropsId, Set<Long> dropsIdSet) {
        if (dropsIdSet.contains(dropsId)) {
            log.warn("getDropsDetail duplicate dropsId={}, dropsIdSet={}", dropsId, dropsIdSet);
            return null;
        }
        dropsIdSet.add(dropsId);
        Drops drops = cellDropsHelper.getDrops(dropsId);
        if (drops == null) {
            log.error("grantDropsToUser invalid dropsId={}", dropsId);
            return null;
        }
        List<DropsItem> dropsItems = cellDropsHelper.listDropsItemByDropsId(dropsId);
        if (CollectionUtils.isEmpty(dropsItems)) {
            log.error("grantDropsToUser empty drop item, dropsId={}", dropsId);
            return null;
        }
        List<DropsItem> items = Lists.newArrayList();
        dropsItems.stream().forEach(dropsItem -> {
            if (DropsItemKind.DROPS.equals(dropsItem.getKind())) {
                DropsBo dropsBo = getDropsDetail(dropsItem.getDropsId(), dropsIdSet);
                if (dropsBo != null) {
                    items.addAll(dropsBo.getDropsItems());
                }
            } else if (DropsItemKind.ITEM.equals(dropsItem.getKind())) {
                items.add(dropsItem);
            } else {
                throw new UnsupportedOperationException();
            }
        });
        return DropsBo.builder()
                .drops(drops)
                .dropsItems(items)
                .build();
    }
}
