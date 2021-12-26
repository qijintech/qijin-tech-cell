package tech.qijin.cell.account.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.account.db.model.DropsItem;
import tech.qijin.cell.account.db.model.Item;

@Data
@Builder
public class DropsItemBo {
    private DropsItem dropsItem;
    private Item item;
}
