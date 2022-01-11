package tech.qijin.cell.feed.server.vo;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedImage;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.service.bo.FeedBo;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.server.vo.ProfileVo;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class FeedVo {
    private Long id;

    private String text;

    private List<String> images;

    private ProfileVo profile;

    private FeedTopicVo topic;

    private FeedCommentsVo comments;

    private FeedLikesVo likes;

    private Boolean hasLiked;

    private String createTime;

    public static FeedVo from(FeedBo feedBo, UserProfile profile) {
        if (feedBo == null) return null;
        Feed feed = feedBo.getFeed();
        List<FeedImage> images = feedBo.getImages();
        return FeedVo.builder()
                .id(feed.getId())
                .text(feed.getText())
                .images(CollectionUtils.isNotEmpty(images)
                        ? images.stream().map(FeedImage::getUrl).collect(Collectors.toList())
                        : Collections.emptyList())
                .profile(ProfileVo.from(profile))
                .hasLiked(feedBo.getHasLiked())
                .createTime(DateUtil.formatSocial(feedBo.getFeed().getCreateTime()))
                .build();
    }

    @Deprecated
    public static FeedVo from(Feed feed, List<FeedImage> images, FeedTopic topic, UserProfile profile) {
        if (feed == null) return null;
        return FeedVo.builder()
                .id(feed.getId())
                .text(feed.getText())
                .images(CollectionUtils.isNotEmpty(images)
                        ? images.stream().map(FeedImage::getUrl).collect(Collectors.toList())
                        : Collections.emptyList())
                .topic(FeedTopicVo.from(topic))
                .profile(ProfileVo.from(profile))
                .createTime(DateUtil.formatSocial(feed.getCreateTime()))
                .build();
    }
}
