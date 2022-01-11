package tech.qijin.cell.feed.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.cell.feed.service.bo.FeedLikeBo;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.server.vo.ProfileVo;
import tech.qijin.util4j.utils.ConvertUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
public class FeedLikeVo {
    private Long id;

    private Long feedId;

    private Long userId;

    private ProfileVo profile;

    private boolean hasLiked;

    public static FeedLikeVo from(FeedLike like, UserProfile profile) {
        FeedLikeVo likeVo = ConvertUtil.convert(like, FeedLikeVo.class);
        if (likeVo != null) {
            likeVo.setProfile(ProfileVo.from(profile));
        }
        return likeVo;
    }

    public static List<FeedLikeVo> from(List<FeedLike> likes, Map<Long, UserProfile> profileMap) {
        if (CollectionUtils.isEmpty(likes)) return Collections.emptyList();
        return likes.stream()
                .map(like -> FeedLikeVo.from(like, profileMap.get(like.getUserId())))
                .collect(Collectors.toList());
    }

    public static FeedLikeVo from(FeedLikeBo likeBo) {
        if (likeBo == null) return null;
        FeedLikeVo likeVo = ConvertUtil.convert(likeBo.getFeedLike(), FeedLikeVo.class);
        if (likeVo != null) {
            likeVo.setProfile(ProfileVo.from(likeBo.getProfile()));
        }
        return likeVo;
    }

    public static List<FeedLikeVo> from(List<FeedLikeBo> likeBos) {
        if (CollectionUtils.isEmpty(likeBos)) return Collections.emptyList();
        return likeBos.stream()
                .map(FeedLikeVo::from)
                .collect(Collectors.toList());
    }
}
