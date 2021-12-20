package tech.qijin.cell.message.helper.impl;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.qijin.cell.message.base.MessageStatus;
import tech.qijin.cell.message.db.dao.MessageContentDao;
import tech.qijin.cell.message.db.dao.MessageDao;
import tech.qijin.cell.message.db.dao.MessageDropsDao;
import tech.qijin.cell.message.db.model.*;
import tech.qijin.cell.message.helper.CellMessageHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellMessageHelperImpl implements CellMessageHelper {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private MessageContentDao messageContentDao;
    @Autowired
    private MessageDropsDao messageDropsDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertMessage(Message message, MessageContent content, List<MessageDrops> messageDrops) {
        MAssert.notNull(content, ResEnum.INVALID_PARAM);
        String res = checkMessage(message);
        if (StringUtils.isNotBlank(res)) {
            log.error("insertMessage message invalid param, res={}, message={}", res, message);
            return false;
        }
        if (StringUtils.isNotBlank(res = checkMessageContent(content))) {
            log.error("insertMessage content invalid param, res={}, message={}", res, content);
            return false;
        }
        message.setStatus(MessageStatus.NORMAL);
        message.setRead(false);
        if (messageDao.insertSelective(message) <= 0) {
            log.error("insertMessage insert message fail, message={}", message);
            return false;
        }
        content.setMessageId(message.getId());
        if (messageContentDao.insertSelective(content) <= 0) {
            log.error("insertMessage insert content fail, message={}", message);
            return false;
        }
        messageDrops.stream().forEach(drops -> drops.setMessageId(message.getId()));
        insertMessageDrops(messageDrops);
        return true;
    }

    @Override
    public List<Message> pageMessage(Long userId, Integer pageNo, Integer pageSize) {
        MessageExample example = new MessageExample();
        example.setOrderByClause(String.format("create_time desc limit %d, %d", (pageNo - 1) * pageSize, pageSize));
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(MessageStatus.NORMAL);
        return messageDao.selectByExample(example);
    }

    @Override
    public MessageContent getMessageContent(Long messageId) {
        MessageContentExample example = new MessageContentExample();
        example.createCriteria()
                .andMessageIdEqualTo(messageId);
        return messageContentDao.selectByExampleWithBLOBs(example).stream().findFirst().orElse(null);
    }

    @Override
    public Map<Long, MessageContent> mapMessageContent(List<Long> messageIds) {
        if (CollectionUtils.isEmpty(messageIds)) return Maps.newHashMap();
        MessageContentExample example = new MessageContentExample();
        example.createCriteria()
                .andMessageIdIn(messageIds);
        return messageContentDao.selectByExampleWithBLOBs(example).stream()
                .collect(Collectors.toMap(MessageContent::getMessageId, Function.identity()));
    }

    @Override
    public Map<Long, List<MessageDrops>> mapMessageDrops(List<Long> messageIds) {
        if (CollectionUtils.isEmpty(messageIds)) return Maps.newHashMap();
        MessageDropsExample example = new MessageDropsExample();
        example.createCriteria()
                .andMessageIdIn(messageIds);
        return messageDropsDao.selectByExample(example).stream().collect(Collectors.groupingBy(MessageDrops::getMessageId));
    }

    @Override
    public void insertMessageDrops(List<MessageDrops> messageDrops) {
        List<MessageDrops> validDrops = messageDrops.stream()
                .filter(drops -> StringUtils.isBlank(checkMessageDrops(drops)))
                .collect(Collectors.toList());
        if (CollectionUtils.isEmpty(validDrops)) {
            return;
        }
        messageDropsDao.batchInsert(validDrops);
    }

    private String checkMessage(Message message) {
        if (!NumberUtil.gtZero(message.getUserId())) return "userId";
        if (message.getKind() == null) return "kind";
        return "";
    }

    private String checkMessageContent(MessageContent content) {
        if (StringUtils.isBlank(content.getContent())) return "content";
        return "";
    }

    private String checkMessageDrops(MessageDrops messageDrops) {
        if (!NumberUtil.gtZero(messageDrops.getMessageId())) return "messageId";
        if (!NumberUtil.gtZero(messageDrops.getDropsId())) return "dropsId";
        if (!NumberUtil.gtZero(messageDrops.getDataId())) return "dataId";
        if (StringUtils.isBlank(messageDrops.getStatementSrc())) return "statementSrc";
        return "";
    }
}
