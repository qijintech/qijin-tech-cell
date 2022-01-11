package tech.qijin.cell.feed.service.bo;

import lombok.Builder;
import lombok.Data;
import tech.qijin.cell.feed.db.model.FeedLike;
import tech.qijin.cell.user.db.model.UserProfile;

@Data
@Builder
public class FeedLikeBo {
    private FeedLike feedLike;
    private UserProfile profile;
}
