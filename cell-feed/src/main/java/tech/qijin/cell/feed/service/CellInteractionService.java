package tech.qijin.cell.feed.service;

import tech.qijin.cell.feed.base.FeedInteractionKind;
import tech.qijin.cell.feed.service.bo.FeedInteractionBo;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;

public interface CellInteractionService {

    /**
     * @param feedId
     * @param fromUserId
     * @param commentId  可以为空
     * @return
     */
    boolean addInteraction(FeedInteractionKind kind,
                           Long feedId,
                           Long fromUserId,
                           Long commentId);

    boolean delInteraction(FeedInteractionKind kind,
                           Long feedId,
                           Long fromUserId,
                           Long commentId);

    boolean delInteraction(Long id, Long fromUserId);

    List<FeedInteractionBo> pageInteraction(Long userId, PageVo pageVo);


}
