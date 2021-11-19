package tech.qijin.cell.feed.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.util4j.utils.ConvertUtil;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class FeedTopicVo {
    private Integer id;

    private String cover;

    private String text;

    private String description;

    public static FeedTopicVo from(FeedTopic feedTopic) {
        return ConvertUtil.convert(feedTopic, FeedTopicVo.class);
    }

    public static List<FeedTopicVo> from(List<FeedTopic> feedTopics) {
        if (CollectionUtils.isEmpty(feedTopics)) return Collections.emptyList();
        return feedTopics.stream().map(FeedTopicVo::from)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
