package tech.qijin.cell.message.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.message.base.CacheKey;
import tech.qijin.cell.message.base.MessageBo;
import tech.qijin.cell.message.base.MessageKind;
import tech.qijin.cell.message.base.MessageWrapper;
import tech.qijin.cell.message.db.model.Message;
import tech.qijin.cell.message.db.model.MessageContent;
import tech.qijin.cell.message.db.model.MessageDrops;
import tech.qijin.cell.message.helper.CellMessageHelper;
import tech.qijin.cell.message.service.CellMessageService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.redis.RedisUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;
import tech.qijin.util4j.web.util.UserUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellMessageServiceImpl implements CellMessageService {
    private Integer defaultPageSize = 10;
    @Autowired
    private CellMessageHelper cellMessageHelper;
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean sendMessage(Long userId, MessageWrapper messageWrapper) {
        Message message = new Message();
        message.setUserId(userId);
        message.setKind(messageWrapper.getKind());
        message.setBrief(messageWrapper.getBrief());
        List<MessageDrops> messageDrops = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(messageWrapper.getDrops())) {
            messageDrops = messageWrapper.getDrops().stream().map(dropsWrapper -> {
                MessageDrops drops = new MessageDrops();
                drops.setDropsId(dropsWrapper.getDropsId());
                drops.setStatementSrc(dropsWrapper.getStatementSrc());
                drops.setDataId(dropsWrapper.getDataId());
                return drops;
            }).collect(Collectors.toList());
        }
        MessageContent content = new MessageContent();
        content.setContent(messageWrapper.getContent());
        if (!cellMessageHelper.insertMessage(message, content, messageDrops)) {
            log.error("sendMessage fail, userId={}, message={}", userId, messageWrapper);
            return false;
        }
        addUnread(userId);
        return true;
    }

    @Override
    public List<MessageBo> pageMessage(Long userId, PageVo pageVo) {
        pageVo = checkPage(pageVo, null);
        List<Message> messages = cellMessageHelper.pageMessage(userId, pageVo.getPageNo(), pageVo.getPageSize());
        if (CollectionUtils.isEmpty(messages)) return Collections.emptyList();
        List<MessageBo> messageBos = wrapWithContent(messages);
        wrapWithDrops(messageBos);
        if (pageVo.isFirstPage()) {
            clearUnread(userId);
        }
        return messageBos;
    }

    @Override
    public MessageBo messageDetail(Long userId, Long messageId) {
        Message message = cellMessageHelper.getMessage(messageId);
        if (message == null) return null;
        MAssert.isTrue(message.getUserId().equals(userId), ResEnum.FORBIDDEN);
        MessageContent content = cellMessageHelper.getMessageContent(messageId);
        cellMessageHelper.updateMessageRead(messageId);
        return MessageBo.builder()
                .message(message)
                .messageContent(content).build();
    }

    @Override
    public Long countMessage(Long userId) {
        Long count = cellMessageHelper.countMessage(userId);
        // 如果没有任何消息，则插入一条欢迎消息
        if (!NumberUtil.gtZero(count)) {
            sendMessage(userId, MessageWrapper.welcome());
            count = 1L;
        }
        return count;
    }

    @Override
    public boolean hasMore(List<MessageBo> messages, PageVo pageVo) {
        if (CollectionUtils.isEmpty(messages)) return false;
        if (messages.size() < pageVo.getPageSize()) return false;
        return true;
    }

    @Override
    public void addUnread(Long userId) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId);
        redisUtil.increment(key, 1L);
    }

    @Override
    public void clearUnread(Long userId) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId);
        redisUtil.delete(key);
    }

    @Override
    public Long countUnread(Long userId) {
        String key = CacheKey.INSTANCE.unreadCountKey(userId);
        Long count = redisUtil.getLong(key);
        return count == null ? 0L : count;
    }

    private List<MessageBo> wrapWithContent(List<Message> messages) {
        Set<Long> messageIds = messages.stream().map(Message::getId).collect(Collectors.toSet());
        Map<Long, MessageContent> contentMap = cellMessageHelper.mapMessageContent(Lists.newArrayList(messageIds));
        return messages.stream()
                .map(message -> MessageBo.builder()
                        .message(message)
                        .messageContent(contentMap.get(message.getId()))
                        .build())
                .collect(Collectors.toList());
    }

    private void wrapWithDrops(List<MessageBo> messageBos) {
        Set<Long> messageIds = messageBos.stream()
                .filter(messageBo -> messageBo.getMessage().getHasDrops())
                .map(messageBo -> messageBo.getMessage().getId())
                .collect(Collectors.toSet());
        Map<Long, List<MessageDrops>> messageDropsMap = cellMessageHelper.mapMessageDrops(Lists.newArrayList(messageIds));
        messageBos.stream().forEach(messageBo -> {
            messageBo.setDrops(messageDropsMap.get(messageBo.getMessage().getId()));
        });
    }

    private PageVo checkPage(PageVo pageVo, Integer customDefaultPageSize) {
        Integer pageSize = customDefaultPageSize == null ? defaultPageSize : customDefaultPageSize;
        if (pageVo == null) {
            return new PageVo(1, pageSize);
        }
        if (!NumberUtil.gtZero(pageVo.getPageNo())) {
            pageVo.setPageNo(1);
        }
        if (!NumberUtil.gtZero(pageVo.getPageSize())) {
            pageVo.setPageSize(pageSize);
        }
        return pageVo;
    }
}
