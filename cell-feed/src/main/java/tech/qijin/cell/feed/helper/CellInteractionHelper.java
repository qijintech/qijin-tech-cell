package tech.qijin.cell.feed.helper;

import tech.qijin.cell.feed.base.FeedInteractionKind;
import tech.qijin.cell.feed.db.model.FeedInteraction;

import java.util.List;

public interface CellInteractionHelper {

    FeedInteraction getInteraction(String format);

    FeedInteraction getInteraction(Long feedId,
                                   Long fromUserId,
                                   FeedInteractionKind kind,
                                   Long commentId);

    /**
     * 添加互动信息
     *
     * @param interaction
     * @return
     */
    boolean addInteraction(FeedInteraction interaction);

    /**
     * 列举互动
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<FeedInteraction> pageInteraction(Long userId, Integer pageNo, Integer pageSize);

    /**
     * 删除互动
     *
     * @param id
     * @return
     */
    boolean updateInteractionValid(Long id, boolean valid);
}
