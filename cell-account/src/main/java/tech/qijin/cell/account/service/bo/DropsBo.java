package tech.qijin.cell.account.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.account.db.model.Drops;
import tech.qijin.cell.account.db.model.DropsItem;

import java.util.List;

@Data
@Builder
public class DropsBo {
    private Drops drops;
    private List<DropsItem> dropsItems;

    private boolean merged;
    // 只有 merged 为true的时候，dropsItemBos 才有值
    private List<DropsItemBo> dropsItemBos;
}
