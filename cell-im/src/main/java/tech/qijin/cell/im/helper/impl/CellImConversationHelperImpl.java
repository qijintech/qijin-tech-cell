package tech.qijin.cell.im.helper.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.db.dao.ImConversationDao;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImConversationExample;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.CellImConversationHelper;
import tech.qijin.cell.im.helper.CellIDGenerator;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;

import java.util.List;
import java.util.Optional;

@Service
public class CellImConversationHelperImpl implements CellImConversationHelper {
    @Autowired
    private ImConversationDao imConversationDao;
    @Autowired
    private CellIDGenerator cellIdGenerator;

    @Override
    public Optional<ImConversation> getConversationByUid(long uid, long peerUid) {
        ImConversationExample example = new ImConversationExample();
        example.createCriteria()
                .andUidEqualTo(uid)
                .andPeerUidEqualTo(peerUid);
        return imConversationDao.selectByExample(example).stream().findFirst();
    }

    @Override
    public ImConversation insertOrUpdateConversation(long uid, long peerUid, ImMessage imMessage) {
        if (!getConversationByUid(uid, peerUid).isPresent()) {
            return insertConversation(uid, peerUid, imMessage);
        } else {
            return updateConversation(uid, peerUid, imMessage);
        }
    }

    @Override
    public List<ImConversation> pageConversation(long uid, long minVersionId, long maxVersionId, int count) {
        ImConversationExample example = new ImConversationExample();
        example.setOrderByClause("version_id desc");
        PageHelper.startPage(0, count);
        example.createCriteria()
                .andUidEqualTo(uid)
                .andVersionIdBetween(minVersionId, maxVersionId);
        return imConversationDao.selectByExample(example);
    }

    private ImConversation insertConversation(long uid, long peerUid, ImMessage imMessage) {
        ImConversation imConversation = new ImConversation();
        imConversation.setUid(uid);
        imConversation.setPeerUid(peerUid);
        imConversation.setVersionId(cellIdGenerator.genVersionId(uid, DateUtil.now()));
        imConversation.setLastMsgId(imMessage.getMsgId());
        imConversation.setLastMsg(JSON.toJSONString(imMessage));
        MAssert.isTrue(imConversationDao.insertSelective(imConversation) > 0, ResEnum.INTERNAL_ERROR);
        return imConversation;
    }

    private ImConversation updateConversation(long uid, long peerUid, ImMessage imMessage) {
        ImConversation imConversation = new ImConversation();
        imConversation.setVersionId(cellIdGenerator.genVersionId(uid, DateUtil.now()));
        imConversation.setLastMsgId(imMessage.getMsgId());
        imConversation.setLastMsg(JSON.toJSONString(imMessage));
        ImConversationExample example = new ImConversationExample();
        example.createCriteria()
                .andUidEqualTo(uid)
                .andPeerUidEqualTo(peerUid);
        MAssert.isTrue(imConversationDao.updateByExampleSelective(imConversation, example) > 0, ResEnum.INSUFFICIENT_BALANCE);
        imConversation.setUid(uid);
        imConversation.setPeerUid(peerUid);
        return imConversation;
    }
}
