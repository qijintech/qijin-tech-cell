package tech.qijin.cell.account.helper.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tech.qijin.cell.account.db.dao.ItemDao;
import tech.qijin.cell.account.db.model.Item;
import tech.qijin.cell.account.db.model.ItemExample;
import tech.qijin.cell.account.helper.CellItemHelper;
import tech.qijin.util4j.web.pojo.LocalCacheAgent;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellItemHelperImpl implements CellItemHelper {
    private LocalCacheAgent<Item> itemCache = new LocalCacheAgent<>();
    @Autowired
    private ItemDao itemDao;

    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void reload() {
        itemCache.process(
                () -> itemDao.getLastUpdatedAt(),
                () -> listItemFromDB());
    }

    private List<Item> listItemFromDB() {
        ItemExample example = new ItemExample();
        return itemDao.selectByExample(example);
    }

    @Override
    public Item getItem(Long itemId) {
        return mapItem().get(itemId);
    }

    @Override
    public List<Item> listItemByIds(List<Long> itemIds, boolean clone) {
        if (CollectionUtils.isEmpty(itemIds)) return Lists.newArrayList();
        Set<Long> itemIdSet = itemIds.stream().collect(Collectors.toSet());
        return listItem(clone).stream()
                .filter(item -> itemIdSet.contains(item.getId()))
                .collect(Collectors.toList());
    }

    private List<Item> listItem(boolean clone) {
        if (clone) return itemCache.clone(Item.class);
        return itemCache.get();
    }

    @Override
    public Map<Long, Item> mapItem() {
        return itemCache.map((Function<Item, Long>) item -> item.getId());
    }

    @Override
    public Map<Long, Item> mapItemByIds(List<Long> itemIds) {
        if (CollectionUtils.isEmpty(itemIds)) return Maps.newHashMap();
        return listItemByIds(itemIds, false).stream()
                .collect(Collectors.toMap(Item::getId, Function.identity()));
    }
}
