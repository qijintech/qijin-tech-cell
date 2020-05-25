package tech.qijin.cell.im.helper.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.db.dao.ImConversationDao;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImConversationExample;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.IDGenerator;
import tech.qijin.cell.im.helper.ImConversationHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;

import java.util.Optional;

@Service
public class ImConversationHelperImpl implements ImConversationHelper {
    @Autowired
    private ImConversationDao imConversationDao;
    @Autowired
    private IDGenerator idGenerator;

    @Override
    public Optional<ImConversation> getConversationByUid(Long uid, Long peerUid) {
        ImConversationExample example = new ImConversationExample();
        example.createCriteria()
                .andUidEqualTo(uid)
                .andPeerUidEqualTo(peerUid);
        return imConversationDao.selectByExample(example).stream().findFirst();
    }

    @Override
    public ImConversation insertOrUpdateConversation(Long uid, Long peerUid, ImMessage imMessage) {
        if (!getConversationByUid(uid, peerUid).isPresent()) {
            return insertConversation(uid, peerUid, imMessage);
        } else {
            return updateConversation(uid, peerUid, imMessage);
        }
    }

    private ImConversation insertConversation(Long uid, Long peerUid, ImMessage imMessage) {
        ImConversation imConversation = new ImConversation();
        imConversation.setUid(uid);
        imConversation.setPeerUid(peerUid);
        imConversation.setVersionId(idGenerator.genVersionId(uid, DateUtil.now()));
        imConversation.setLastMsg(JSON.toJSONString(imMessage));
        MAssert.isTrue(imConversationDao.insertSelective(imConversation) > 0, ResEnum.INTERNAL_ERROR);
        return imConversation;
    }

    private ImConversation updateConversation(Long uid, Long peerUid, ImMessage imMessage) {
        ImConversation imConversation = new ImConversation();
        imConversation.setVersionId(idGenerator.genVersionId(uid, DateUtil.now()));
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
