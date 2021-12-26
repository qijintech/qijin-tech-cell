package tech.qijin.cell.account.helper.impl;

import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.statement.drop.Drop;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tech.qijin.cell.account.db.dao.DropsDao;
import tech.qijin.cell.account.db.dao.DropsItemDao;
import tech.qijin.cell.account.db.model.Drops;
import tech.qijin.cell.account.db.model.DropsExample;
import tech.qijin.cell.account.db.model.DropsItem;
import tech.qijin.cell.account.db.model.DropsItemExample;
import tech.qijin.cell.account.helper.CellDropsHelper;
import tech.qijin.util4j.web.pojo.LocalCacheAgent;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellDropsHelperImpl implements CellDropsHelper {
    private LocalCacheAgent dropsCache = new LocalCacheAgent<Drops>();
    private LocalCacheAgent dropsItemCache = new LocalCacheAgent<DropsItem>();
    private Map<Long, List<DropsItem>> dropsAndItemsMap = Maps.newHashMap();
    @Autowired
    private DropsDao dropsDao;
    @Autowired
    private DropsItemDao dropsItemDao;

    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void reload() {
        dropsCache.process(
                () -> dropsDao.getLastUpdatedAt(),
                () -> loadDropsFromDB());
        dropsItemCache.process(
                () -> dropsItemDao.getLastUpdatedAt(),
                () -> loadDropsItemFromDB());
        dropsAndItemsMap = ((List<DropsItem>) dropsItemCache.get()).stream()
                .collect(Collectors.groupingBy(DropsItem::getDropsId));
    }

    @Override
    public Drops getDrops(Long dropsId) {
        return mapDrops().get(dropsId);
    }

    @Override
    public List<Drops> listDrops(boolean clone) {
        if (clone) return dropsCache.clone(Drops.class);
        return dropsCache.get();
    }

    @Override
    public List<Drops> listDropsByIds(List<Long> dropsIds, boolean clone) {
        if (CollectionUtils.isEmpty(dropsIds)) return Collections.emptyList();
        Set<Long> dropsIdSet = dropsIds.stream().collect(Collectors.toSet());
        List<Drops> dropsList = listDrops(clone);
        return dropsList.stream()
                .filter(drops -> dropsIdSet.contains(drops.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public DropsItem getDropsItem(Long dropsItemId) {
        return mapDropsItem(dropsItemId).get(dropsItemId);
    }

    @Override
    public List<DropsItem> listDropsItems(boolean clone) {
        if (clone) return dropsItemCache.clone(DropsItem.class);
        return dropsItemCache.get();
    }

    @Override
    public List<DropsItem> listDropsItemsByIds(List<Long> dropsItemIds, boolean clone) {
        if (CollectionUtils.isEmpty(dropsItemIds)) return Collections.emptyList();
        Set<Long> dropsItemIdSet = dropsItemIds.stream().collect(Collectors.toSet());
        return listDropsItems(clone).stream()
                .filter(item -> dropsItemIdSet.contains(item.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public Map<Long, DropsItem> mapDropsItem(Long dropsItemId) {
        return dropsItemCache.map((Function<DropsItem, Long>) item -> item.getId());
    }

    @Override
    public List<DropsItem> listDropsItemByDropsId(Long dropsId) {
        return dropsAndItemsMap.get(dropsId);
    }

    @Override
    public Map<Long, List<DropsItem>> mapDropsWithItemsByDropsIds(List<Long> dropsIds) {
        if (CollectionUtils.isEmpty(dropsIds)) return Maps.newHashMap();
        return dropsIds.stream()
                .collect(Collectors.toMap(Function.identity(), dropsId -> dropsAndItemsMap.get(dropsId)));
    }

    @Override
    public Map<Long, Drops> mapDrops() {
        return dropsCache.map((Function<Drops, Long>) drops -> drops.getId());
    }

    private List<Drops> loadDropsFromDB() {
        DropsExample example = new DropsExample();
        return dropsDao.selectByExample(example);
    }

    private List<DropsItem> loadDropsItemFromDB() {
        DropsItemExample example = new DropsItemExample();
        return dropsItemDao.selectByExample(example);
    }
}
