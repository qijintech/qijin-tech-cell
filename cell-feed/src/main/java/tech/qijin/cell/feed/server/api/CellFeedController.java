package tech.qijin.cell.feed.server.api;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.server.vo.FeedInteractionVo;
import tech.qijin.cell.feed.server.vo.FeedInteractionsVo;
import tech.qijin.cell.feed.server.vo.FeedTopicVo;
import tech.qijin.cell.feed.server.vo.FeedTopicsVo;
import tech.qijin.cell.feed.service.CellFeedService;
import tech.qijin.cell.feed.service.CellInteractionService;
import tech.qijin.cell.feed.service.bo.FeedInteractionBo;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.web.pojo.ResultVo;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/api/v1/feed")
public class CellFeedController {
    @Autowired
    private CellFeedService cellFeedService;
    @Autowired
    private CellInteractionService cellInteractionService;
    @Autowired
    private CellUserProfileService cellUserProfileService;

    @GetMapping("/interaction/list")
    public FeedInteractionsVo listInteractions(PageVo pageVo) {
        List<FeedInteractionBo> interactionBos = cellInteractionService.pageInteraction(UserUtil.getUserId(), pageVo);
        Set<Long> userIds = interactionBos.stream()
                .map(interactionBo -> interactionBo.getInteraction().getFromUserId())
                .collect(Collectors.toSet());
        Map<Long, UserProfile> profileMap = cellUserProfileService.mapProfile(Lists.newArrayList(userIds));
        List<FeedInteractionVo> interactionVos = FeedInteractionVo.from(interactionBos, profileMap);
        return FeedInteractionsVo.builder()
                .interactions(interactionVos)
                .build();
    }

    @GetMapping("/topic/list")
    public FeedTopicsVo listFeedTopic(PageVo pageVo) {
        List<FeedTopic> feedTopics = cellFeedService.pageFeedTopic(pageVo);
        return FeedTopicsVo.builder()
                .topics(FeedTopicVo.from(feedTopics))
                .build();
    }
}
