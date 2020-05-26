package tech.qijin.cell.im.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.helper.CellImConversationHelper;
import tech.qijin.cell.im.service.CellImConversationService;

import java.util.List;

/**
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class CellImConversationServiceImpl implements CellImConversationService {
    @Autowired
    private CellImConversationHelper cellImConversationHelper;

    @Override
    public List<ImConversation> listConversationHistory(Long uid, Long versionId, Integer count) {
        return null;
    }

    @Override
    public List<ImConversation> listConversationNew(Long uid, Long versionId, Integer count) {
        return null;
    }

    @Override
    public boolean clearConversation(Long uid, Long peerUid) {
        return false;
    }

    @Override
    public boolean deleteConversation(Long uid, Long peerUid) {
        return false;
    }
}
