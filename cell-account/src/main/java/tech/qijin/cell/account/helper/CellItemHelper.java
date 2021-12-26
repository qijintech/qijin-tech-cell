package tech.qijin.cell.account.helper;

import tech.qijin.cell.account.db.model.Item;

import java.util.List;
import java.util.Map;

public interface CellItemHelper {
    Item getItem(Long itemId);

    List<Item> listItemByIds(List<Long> itemIds, boolean clone);

    Map<Long, Item> mapItem();
    Map<Long, Item> mapItemByIds(List<Long> itemIds);
}
