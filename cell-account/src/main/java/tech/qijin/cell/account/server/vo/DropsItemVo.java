package tech.qijin.cell.account.server.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.account.base.AccountKind;
import tech.qijin.cell.account.db.model.DropsItem;
import tech.qijin.cell.account.db.model.Item;
import tech.qijin.cell.account.service.bo.DropsItemBo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class DropsItemVo {
    private String name;

    private AccountKind kind;

    private Long amount;

    public static DropsItemVo from(DropsItemBo itemBo) {
        if (itemBo == null
                || itemBo.getDropsItem() == null
                || itemBo.getItem() == null) return null;
        DropsItem dropsItem = itemBo.getDropsItem();
        Item item = itemBo.getItem();
        return DropsItemVo.builder()
                .name(item.getName())
                .kind(item.getKind())
                .amount(dropsItem.getAmount())
                .build();
    }

    public static List<DropsItemVo> from(List<DropsItemBo> dropsItemBos) {
        if (CollectionUtils.isEmpty(dropsItemBos)) return Collections.emptyList();
        return dropsItemBos.stream()
                .map(DropsItemVo::from)
                .collect(Collectors.toList());
    }
}
