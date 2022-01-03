package tech.qijin.cell.message.service;

import tech.qijin.cell.message.base.MessageBo;
import tech.qijin.cell.message.base.MessageWrapper;
import tech.qijin.cell.message.db.model.Message;
import tech.qijin.util4j.lang.vo.PageVo;

import java.util.List;

public interface CellMessageService {
    /**
     * 发送站内信
     *
     * @param userId
     * @param messageWrapper
     */
    boolean sendMessage(Long userId, MessageWrapper messageWrapper);

    /**
     * 分页获取站内信
     *
     * @param userId
     * @param pageVo
     * @return
     */
    List<MessageBo> pageMessage(Long userId, PageVo pageVo);

    /**
     * 消息总数
     *
     * @param userId
     * @return
     */
    Long countMessage(Long userId);

    /**
     * 查看是否还有更多消息
     *
     * @param messages
     * @param pageVo
     * @return
     */
    boolean hasMore(List<MessageBo> messages, PageVo pageVo);

    /**
     * 增加未读数
     *
     * @param userId
     */
    void addUnread(Long userId);

    /**
     * 清除未读数
     *
     * @param userId
     */
    void clearUnread(Long userId);

    /**
     * 获取未读数
     *
     * @param userId
     * @return
     */
    Long countUnread(Long userId);

}
