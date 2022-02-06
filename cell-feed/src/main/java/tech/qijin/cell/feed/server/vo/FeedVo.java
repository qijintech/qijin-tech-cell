package tech.qijin.cell.feed.server.vo;

import com.google.common.collect.Lists;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.feed.base.FeedType;
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

    private String firstImage;

    private ProfileVo profile;

    private FeedTopicVo topic;

    private FeedCommentsVo comments;

    private FeedLikesVo likes;

    private Boolean hasLiked;

    private String createTime;

    private boolean deleted;

    public static FeedVo from(FeedBo feedBo, UserProfile profile) {
        if (feedBo == null) return null;
        Feed feed = feedBo.getFeed();
        List<FeedImage> images = feedBo.getImages();
        FeedVo feedVo = FeedVo.builder()
                .id(feed.getId())
                .text(feed.getText())
                .images(CollectionUtils.isNotEmpty(images)
                        ? images.stream().map(FeedImage::getUrl).collect(Collectors.toList())
                        : Collections.emptyList())
                .firstImage(CollectionUtils.isNotEmpty(images) ? images.get(0).getUrl() : null)
                .profile(ProfileVo.from(profile))
                .hasLiked(feedBo.getHasLiked())
                .createTime(DateUtil.formatSocial(feedBo.getFeed().getCreateTime()))
                .build();
        return handleDeleted(feedVo, feedBo.getFeed());
    }

    @Deprecated
    public static FeedVo from(Feed feed, List<FeedImage> images, FeedTopic topic, UserProfile profile) {
        if (feed == null) return null;
        FeedVo feedVo = FeedVo.builder()
                .id(feed.getId())
                .text(feed.getText())
                .images(CollectionUtils.isNotEmpty(images)
                        ? images.stream().map(FeedImage::getUrl).collect(Collectors.toList())
                        : Collections.emptyList())
                .firstImage(CollectionUtils.isNotEmpty(images) ? images.get(0).getUrl() : null)
                .topic(FeedTopicVo.from(topic))
                .profile(ProfileVo.from(profile))
                .createTime(DateUtil.formatSocial(feed.getCreateTime()))
                .build();
        return handleDeleted(feedVo, feed);
    }

    private static FeedVo handleDeleted(FeedVo feedVo, Feed feed) {
        if (feedVo == null) return null;
        if (FeedType.DELETED.equals(feed.getType())) {
            feedVo.setImages(Lists.newArrayList());
            feedVo.setText("");
            feedVo.setDeleted(true);
        }
        return feedVo;
    }
}
