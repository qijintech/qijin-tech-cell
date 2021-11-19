package tech.qijin.cell.feed.server.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedTopic;
import tech.qijin.cell.feed.server.vo.FeedTopicVo;
import tech.qijin.cell.feed.server.vo.FeedTopicsVo;
import tech.qijin.cell.feed.service.CellFeedService;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.web.pojo.ResultVo;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/feed")
public class CellFeedController {
    @Autowired
    private CellFeedService cellFeedService;

    @GetMapping("/topic/list")
    public FeedTopicsVo listFeedTopic(PageVo pageVo) {
        List<FeedTopic> feedTopics = cellFeedService.pageFeedTopic(pageVo);
        return FeedTopicsVo.builder()
                .topics(FeedTopicVo.from(feedTopics))
                .build();
    }
}
