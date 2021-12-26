package tech.qijin.cell.account.helper;

import tech.qijin.cell.account.db.model.Drops;
import tech.qijin.cell.account.db.model.DropsItem;

import java.util.List;
import java.util.Map;

public interface CellDropsHelper {
    /**
     * 按照id获取Drops
     *
     * @param dropsId
     * @return
     */
    Drops getDrops(Long dropsId);

    /**
     * 获取所有Drops
     *
     * @return
     */
    List<Drops> listDrops(boolean clone);
    List<Drops> listDropsByIds(List<Long> dropsIds, boolean clone);

    /**
     * Drops id和entity的映射
     *
     * @return
     */
    Map<Long, Drops> mapDrops();

    DropsItem getDropsItem(Long dropsItemId);

    List<DropsItem> listDropsItems(boolean clone);
    List<DropsItem> listDropsItemsByIds(List<Long> dropsItemIds, boolean clone);

    Map<Long, DropsItem> mapDropsItem(Long dropsItemId);

    List<DropsItem> listDropsItemByDropsId(Long dropsId);

    Map<Long, List<DropsItem>> mapDropsWithItemsByDropsIds(List<Long> dropsIds);

}
