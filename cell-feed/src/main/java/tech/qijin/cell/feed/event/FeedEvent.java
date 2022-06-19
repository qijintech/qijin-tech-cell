package tech.qijin.cell.feed.event;

import lombok.Builder;
import lombok.Data;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.collections.map.HashedMap;

import java.util.Map;

@Data
@Builder
public class FeedEvent {
    private FeedEventType type;
    private Long feedId;
    private Map<String, Object> extra;

    public FeedEvent extra(String key, Object value) {
        if (MapUtils.isEmpty(this.extra)) {
            this.extra = new HashedMap();
        }
        this.extra.put(key, value);
        return this;
    }
}
