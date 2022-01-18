package tech.qijin.cell.feed.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.base.FeedInteractionKind;
import tech.qijin.cell.feed.db.model.Feed;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedInteraction;
import tech.qijin.cell.feed.helper.CellFeedHelper;
import tech.qijin.cell.feed.helper.CellInteractionHelper;
import tech.qijin.cell.feed.service.CellCommentService;
import tech.qijin.cell.feed.service.CellFeedService;
import tech.qijin.cell.feed.service.CellInteractionService;
import tech.qijin.cell.feed.service.bo.FeedBo;
import tech.qijin.cell.feed.service.bo.FeedInteractionBo;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellInteractionServiceImpl implements CellInteractionService {
    @Autowired
    private CellInteractionHelper cellInteractionHelper;
    @Autowired
    private CellFeedHelper cellFeedHelper;
    @Autowired
    private CellFeedService cellFeedService;
    @Autowired
    private CellCommentService cellCommentService;

    @Override
    public boolean addInteraction(FeedInteractionKind kind,
                                  Long feedId,
                                  Long fromUserId,
                                  Long commentId) {

        Feed feed = cellFeedHelper.getFeedById(feedId);
        MAssert.notNull(feed, ResEnum.INVALID_PARAM);
        return addInteraction(kind, feedId, feed.getUserId(), fromUserId, commentId);
    }

    @Override
    public boolean addInteraction(FeedInteractionKind kind,
                                  Long feedId,
                                  Long userId,
                                  Long fromUserId,
                                  Long commentId) {
        String format = format(kind, userId, fromUserId, feedId, commentId);
        FeedInteraction interaction = cellInteractionHelper.getInteraction(format);
        if (interaction != null) {
            if (interaction.getValid()) return true;
            return cellInteractionHelper.updateInteractionValid(interaction.getId(), true);
        }
        interaction = new FeedInteraction();
        interaction.setUserId(userId);
        interaction.setFromUserId(fromUserId);
        interaction.setFeedId(feedId);
        interaction.setCommentId(commentId);
        interaction.setFormat(format);
        interaction.setKind(kind);
        return cellInteractionHelper.addInteraction(interaction);
    }

    private String format(FeedInteractionKind kind,
                          Long userId,
                          Long fromUserId,
                          Long feedId,
                          Long commentId) {

        return Strings.join(Lists.newArrayList(kind.name(),
                String.valueOf(userId),
                String.valueOf(fromUserId),
                String.valueOf(feedId),
                String.valueOf(commentId)),
                ':');
    }

    @Override
    public boolean delInteraction(FeedInteractionKind kind,
                                  Long feedId,
                                  Long fromUserId,
                                  Long commentId) {
        Feed feed = cellFeedHelper.getFeedById(feedId);
        MAssert.notNull(feed, ResEnum.INVALID_PARAM);
        return delInteraction(kind, feedId, feed.getUserId(), fromUserId, commentId);
    }

    @Override
    public boolean delInteraction(FeedInteractionKind kind,
                                  Long feedId,
                                  Long userId,
                                  Long fromUserId,
                                  Long commentId) {
        String format = format(kind, userId, fromUserId, feedId, commentId);
        FeedInteraction interaction = cellInteractionHelper.getInteraction(format);
        if (interaction == null) return true;
        if (!interaction.getValid()) return true;
        return cellInteractionHelper.updateInteractionValid(interaction.getId(), false);
    }

    @Override
    public List<FeedInteractionBo> pageInteraction(Long userId, PageVo pageVo) {
        pageVo = PageVo.check(pageVo, null);
        List<FeedInteraction> interactions = cellInteractionHelper.pageInteraction(userId, pageVo.getPageNo(), pageVo.getPageSize());
        if (CollectionUtils.isEmpty(interactions)) return Collections.emptyList();
        Set<Long> feedIds = interactions.stream().map(FeedInteraction::getFeedId).collect(Collectors.toSet());
        List<Feed> feeds = cellFeedHelper.listFeedByIds(Lists.newArrayList(feedIds));
        List<FeedBo> feedBos = cellFeedService.withFeedImage(feeds);
        Map<Long, FeedBo> feedBoMap = feedBos.stream()
                .collect(Collectors.toMap(feedBo -> feedBo.getFeed().getId(), Function.identity()));

        Set<Long> commentIds = interactions.stream()
                .filter(interaction -> NumberUtil.gtZero(interaction.getCommentId()))
                .map(FeedInteraction::getCommentId)
                .collect(Collectors.toSet());
        Map<Long, FeedComment> commentMap = cellCommentService.mapComment(Lists.newArrayList(commentIds));

        return interactions.stream().map(interaction ->
                FeedInteractionBo.builder()
                        .interaction(interaction)
                        .feedBo(feedBoMap.get(interaction.getFeedId()))
                        .comment(commentMap.get(interaction.getCommentId()))
                        .build())
                .collect(Collectors.toList());
    }
}
