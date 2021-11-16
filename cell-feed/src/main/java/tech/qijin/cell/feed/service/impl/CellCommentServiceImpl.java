package tech.qijin.cell.feed.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.helper.CellCommentHelper;
import tech.qijin.cell.feed.service.CellCommentService;
import tech.qijin.cell.feed.service.CommonService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CellCommentServiceImpl extends CommonService implements CellCommentService {
    private Integer defaultPageSize = 10;
    @Autowired
    private CellCommentHelper cellCommentHelper;

    @Override
    public boolean addFeedComment(Long userId, Long feedId, String commentText, String commentImage) {
        FeedComment comment = new FeedComment();
        comment.setUserId(userId);
        comment.setFeedId(feedId);
        comment.setContentText(commentText);
        comment.setContentImage(commentImage);
        comment.setValid(true);
        return cellCommentHelper.insertComment(comment);
    }

    @Override
    public boolean replyComment(Long userId,
                                Long feedId,
                                Long commentId,
                                Long subCommentId,
                                String commentText,
                                String commentImage) {
        MAssert.isTrue(NumberUtil.gtZero(commentId), ResEnum.INVALID_PARAM);
        FeedComment comment = new FeedComment();
        comment.setUserId(userId);
        comment.setFeedId(feedId);
        comment.setContentText(commentText);
        comment.setContentImage(commentImage);
        comment.setToCommentId(commentId);
        comment.setToSubCommentId(subCommentId);
        comment.setValid(true);
        return cellCommentHelper.insertCommentReply(comment);
    }

    @Override
    public Integer countFeedComment(Long feeId) {
        return cellCommentHelper.countFeedComment(feeId);
    }

    @Override
    public Integer countCommentReply(Long commentId) {
        return cellCommentHelper.countCommentReply(commentId);
    }

    @Override
    public List<FeedComment> pageFeedComment(Long feedId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        return cellCommentHelper.pageFeedComment(feedId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public List<FeedComment> pageCommentReply(Long commentId, PageVo pageVo) {
        pageVo = checkPage(pageVo, 5);
        return cellCommentHelper.pageCommentReply(commentId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    @Override
    public Map<Long, FeedComment> mapComment(List<Long> commentIds) {
        return cellCommentHelper.mapComment(commentIds);
    }

    @Override
    public Map<Long, List<FeedComment>> mapCommentReplies(List<Long> toCommentIds) {
        return cellCommentHelper.mapCommentReplies(toCommentIds);
    }
}
