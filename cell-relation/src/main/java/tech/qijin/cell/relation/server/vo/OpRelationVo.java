package tech.qijin.cell.relation.server.vo;

import lombok.Data;
import tech.qijin.cell.relation.bo.OpRelationBo;
import tech.qijin.util4j.utils.ConvertUtil;

@Data
public class OpRelationVo {
    private boolean showToast;

    public static OpRelationVo from(OpRelationBo opRelationBo) {
        return ConvertUtil.convert(opRelationBo, OpRelationVo.class);
    }
}
