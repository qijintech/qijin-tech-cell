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
    public boolean saveMessage(ImMessage ImMessage) {
        // TODO 缓存部分数据
        // 插入到DB中
        return imMessageDao.insertSelective(ImMessage) > 0;
    }

    @Override
    public Optional<ImMessage> getMessageByMsgId(Long msgId) {
        ImMessageExample example = new ImMessageExample();
        example.createCriteria()
                .andMsgIdEqualTo(msgId);
        return imMessageDao.selectByExample(example).stream().findFirst();
    }

    @Override
    public List<ImMessage> pageMessage(Long uid, Long peerUid, Long maxMsgId, Long minMsgId, int count) {
        PageHelper.startPage(0, count);
        List<Integer> statusList = Lists.newArrayList();
        statusList.add(0);
        statusList.add(uid > peerUid ? MessageUtil.SmallerDelete(0) : MessageUtil.LargerDelete(0));
        ImMessageExample example = new ImMessageExample();
        example.setOrderByClause("msg_id desc");
        example.createCriteria()
                .andUnionIdEqualTo(formatUnionId(uid, peerUid))
                .andMsgIdLessThan(maxMsgId)
                .andStatusIn(statusList)
                .andMsgIdGreaterThan(minMsgId);
        return imMessageDao.selectByExample(example);
    }

    private boolean insertMessage(ImMessage messageInfo) {
        // TODO
        // 存储到数据库
        // 更新缓存。出现异常不影响主流程
//        Util.runIgnoreEx(() -> refreshMessageCache(), "refresh message cache fail");
        return true;
    }

    private String formatUnionId(Long uid, Long toUid) {
        return uid > toUid ?
                String.format("%d:%d", toUid, uid) :
                String.format("%d:%d", uid, toUid);
    }
}
