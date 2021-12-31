package tech.qijin.cell.relation.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.db.model.Relation;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.server.vo.ProfileVo;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class RelationVo {
    private Long id;

    private Long peerUserId;

    private ProfileVo profile;

    private RelationKind kind;

    private Date relationTime;

    private String relationTimeStr;

    public static RelationVo from(Relation relation, UserProfile profile) {
        RelationVo relationVo = ConvertUtil.convert(relation, RelationVo.class);
        if (relationVo != null) {
            relationVo.setProfile(ProfileVo.from(profile));
            relationVo.setRelationTimeStr(DateUtil.formatStr(relation.getRelationTime(), DateUtil.DATETIME_FORMAT));
        }
        return relationVo;
    }

    public static List<RelationVo> from(List<Relation> relations, Map<Long, UserProfile> profileMap) {
        if (CollectionUtils.isEmpty(relations)) return Collections.emptyList();
        return relations.stream()
                .map(relation -> RelationVo.from(relation, profileMap.get(relation.getUserId())))
                .collect(Collectors.toList());
    }
}
