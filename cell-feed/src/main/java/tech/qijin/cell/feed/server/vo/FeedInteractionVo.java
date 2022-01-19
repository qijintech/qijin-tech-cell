package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.base.FeedInteractionKind;
import tech.qijin.cell.feed.service.bo.FeedInteractionBo;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.server.vo.ProfileVo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Builder
public class FeedInteractionVo {
    private Long feedId;
    private ProfileVo fromUser;
    private FeedVo feed;
    private boolean isLike;
    private FeedCommentVo comment;

    public static FeedInteractionVo from(FeedInteractionBo interactionBo, Map<Long, UserProfile> profileMap) {
        if (interactionBo == null) return null;
        return FeedInteractionVo.builder()
                .feedId(interactionBo.getFeedBo().getFeed().getId())
                .fromUser(ProfileVo.from(profileMap.get(interactionBo.getInteraction().getFromUserId())))
                .feed(FeedVo.from(interactionBo.getFeedBo(), null))
                .isLike(interactionBo.getInteraction().getKind().isLike())
                .comment(FeedCommentVo.from(interactionBo.getComment(), null))
                .build();
    }

    public static List<FeedInteractionVo> from(List<FeedInteractionBo> interactionBos, Map<Long, UserProfile> profileMap) {
        return interactionBos.stream()
                .map(interactionBo -> FeedInteractionVo.from(interactionBo, profileMap))
                .collect(Collectors.toList());
    }
}
