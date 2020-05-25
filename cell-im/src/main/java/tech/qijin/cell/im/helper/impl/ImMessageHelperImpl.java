package tech.qijin.cell.im.helper.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.db.dao.ImMessageDao;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.db.model.ImMessageExample;
import tech.qijin.cell.im.helper.IDGenerator;
import tech.qijin.cell.im.helper.ImMessageHelper;
import tech.qijin.cell.im.util.MessageUtil;
import tech.qijin.util4j.utils.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ImMessageHelperImpl implements ImMessageHelper {
    @Autowired
    private IDGenerator idGenerator;
    @Autowired
    private ImMessageDao imMessageDao;

    @Override
    public ImMessage convertMessage(MessageSendVO messageSendVO) {
        Date now = DateUtil.now();
        // 获取msgId，versionId等
        Long msgId = idGenerator.genMsgId(now);
//        Long seqId = idGenerator.genSeqID(now);
        ImMessage messageInfo = new ImMessage();
        messageInfo.setUnionId(formatUnionId(messageSendVO.getUid(), messageSendVO.getToUid()));
        messageInfo.setFromUid(messageSendVO.getUid());
        messageInfo.setMsgId(msgId);
        messageInfo.setContent(JSON.toJSONString(messageSendVO.getContent()));
        messageInfo.setExtra(JSON.toJSONString(messageSendVO.getExt()));
        return messageInfo;
    }

    @Override
    public boolean saveMessage(ImMessage imMessage) {
        // TODO 缓存部分数据
        insertMessageToCache(imMessage);
        // 插入到DB中
        return insertMessageToDB(imMessage);
    }

    @Override
    public Optional<ImMessage> getMessageByMsgId(Long msgId) {
        ImMessageExample example = new ImMessageExample();
        example.createCriteria()
                .andMsgIdEqualTo(msgId);
        return imMessageDao.selectByExample(example).stream().findFirst();
    }

    @Override
    public Optional<ImMessage> getMessageByUidAndMsgId(Long uid, Long peerUid, Long msgId) {
        ImMessageExample example = new ImMessageExample();
        example.createCriteria()
                .andUnionIdEqualTo(formatUnionId(uid, peerUid))
                .andMsgIdEqualTo(msgId);
        return imMessageDao.selectByExample(example).stream().findFirst();
    }

    @Override
    public List<ImMessage> pageMessage(Long uid, Long peerUid, Long maxMsgId, Long minMsgId, int count) {
        PageHelper.startPage(0, count);
        List<Integer> statusList = Lists.newArrayList();
        statusList.add(0);
        statusList.add(uid > peerUid ? MessageUtil.smallerDelete(0) : MessageUtil.largerDelete(0));
        ImMessageExample example = new ImMessageExample();
        example.setOrderByClause("msg_id desc");
        example.createCriteria()
                .andUnionIdEqualTo(formatUnionId(uid, peerUid))
                .andMsgIdLessThan(maxMsgId)
                .andStatusIn(statusList)
                .andMsgIdGreaterThan(minMsgId);
        return imMessageDao.selectByExample(example);
    }

    @Override
    public boolean updateMessageStatus(long msgId, int originStatus, int toStatus) {
        ImMessage message = new ImMessage();
        message.setStatus(toStatus);

        ImMessageExample example = new ImMessageExample();
        example.createCriteria().andMsgIdEqualTo(msgId)
                .andStatusEqualTo(originStatus);
        return imMessageDao.updateByExampleSelective(message, example) > 0;
    }

    @Override
    public Optional<ImMessage> getPreMessage(long uid, long peerUid, long msgId, long minMsgId) {
        return pageMessage(uid, peerUid, msgId, minMsgId, 1).stream().findFirst();
    }

    private boolean insertMessageToCache(ImMessage imMessage) {
        // TODO
        // 更新缓存。出现异常不影响主流程
//        Util.runIgnoreEx(() -> refreshMessageCache(), "refresh message cache fail");
        return true;
    }

    private boolean insertMessageToDB(ImMessage imMessage) {
        return imMessageDao.insertSelective(imMessage) > 0;
    }

    private String formatUnionId(Long uid, Long toUid) {
        return uid > toUid ?
                String.format("%d:%d", toUid, uid) :
                String.format("%d:%d", uid, toUid);
    }
}
