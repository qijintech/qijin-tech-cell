package tech.qijin.cell.im.base;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.im.base.AbstractContent;
import tech.qijin.cell.im.db.model.ImMessage;

import java.util.Map;

@Data
@Builder
public class CellImMessageBo {
    private ImMessage imMessage;
    private Long toUid;
    private AbstractContent content;
    private Map<String, Object> extra;
}
