package tech.qijin.cell.message.helper;

import tech.qijin.cell.message.db.model.Message;
import tech.qijin.cell.message.db.model.MessageContent;
import tech.qijin.cell.message.db.model.MessageDrops;

import java.util.List;
import java.util.Map;

public interface CellMessageHelper {
    /**
     * 插入站内信
     *
     * @param message
     * @param content
     * @return
     */
    boolean insertMessage(Message message, MessageContent content, List<MessageDrops> messageDrops);

    /**
     * 分页查询message
     *
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<Message> pageMessage(Long userId, Integer pageNo, Integer pageSize);

    /**
     * 消息总数
     *
     * @param userId
     * @return
     */
    Long countMessage(Long userId);

    /**
     * 查询message content
     *
     * @param messageId
     * @return
     */
    MessageContent getMessageContent(Long messageId);

    /**
     * 查询message内容
     *
     * @param messageIds
     * @return
     */
    Map<Long, MessageContent> mapMessageContent(List<Long> messageIds);

    /**
     * 添加message领奖信息
     *
     * @param messageDrops
     */
    void insertMessageDrops(List<MessageDrops> messageDrops);

    /**
     * 获取message对应的奖励
     *
     * @param messageIds
     * @return
     */
    Map<Long, List<MessageDrops>> mapMessageDrops(List<Long> messageIds);
}
