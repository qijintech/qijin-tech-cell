package tech.qijin.cell.relation.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.qijin.cell.relation.base.RelationKind;
import tech.qijin.cell.relation.db.model.Relation;
import tech.qijin.cell.relation.server.vo.RelationReqVo;
import tech.qijin.cell.relation.server.vo.RelationVo;
import tech.qijin.cell.relation.server.vo.RelationsVo;
import tech.qijin.cell.relation.service.CellRelationService;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/relation/")
public class CellRelationController {
    @Autowired
    private CellRelationService cellRelationService;
    @Autowired
    private CellUserProfileService cellUserProfileService;

    @GetMapping("/list")
    public RelationsVo listRelation(RelationKind kind, PageVo pageVo) {
        MAssert.notNull(kind, ResEnum.INVALID_PARAM);
        List<Relation> relations = cellRelationService.pageRelation(UserUtil.getUserId(), kind, pageVo);
        List<Long> userIds = relations.stream()
                .map(Relation::getUserId)
                .collect(Collectors.toList());
        Map<Long, UserProfile> profileMap = cellUserProfileService.mapProfile(userIds);
        return RelationsVo.builder()
                .relations(RelationVo.from(relations, profileMap))
                .build();
    }

    @PostMapping("/add")
    public boolean addRelation(@RequestBody RelationReqVo relationReqVo) {
        MAssert.notNull(relationReqVo, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(relationReqVo.getPeerUserId()), ResEnum.INVALID_PARAM);
        MAssert.notNull(relationReqVo.getKind(), ResEnum.INVALID_PARAM);
        return cellRelationService.addRelation(UserUtil.getUserId(),
                relationReqVo.getPeerUserId(),
                relationReqVo.getKind());
    }

    @GetMapping("/count")
    public Long relationCount(RelationKind kind) {
        return cellRelationService.countRelation(UserUtil.getUserId(), kind);
    }

    @GetMapping("/unread/count")
    public Long unreadCount(RelationKind kind) {
        return cellRelationService.countUnread(UserUtil.getUserId(), kind);
    }
}
