package tech.qijin.cell.im.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.helper.CellImConversationHelper;
import tech.qijin.cell.im.helper.CellImUnreadHelper;
import tech.qijin.cell.im.service.CellImConversationService;
import tech.qijin.util4j.utils.NumberUtil;

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
        if (NumberUtil.nullOrZero(versionId)) {
            versionId = Long.MAX_VALUE;
        }
        count = NumberUtil.nullOrZero(count) ? Constants.DEFAULT_PAGE_SIZE : count;
        count = count > Constants.MAX_PAGE_SIZE ? Constants.MAX_PAGE_SIZE : count;
        return cellImConversationHelper.pageConversation(uid, 0, versionId, count);
    }

    @Override
    public List<ImConversation> listConversationNew(Long uid, Long versionId, Integer count) {
        if (NumberUtil.nullOrZero(versionId)) {
            versionId = 0L;
        }
        count = NumberUtil.nullOrZero(count) ? Constants.DEFAULT_PAGE_SIZE : count;
        count = count > Constants.MAX_PAGE_SIZE ? Constants.MAX_PAGE_SIZE : count;
        return cellImConversationHelper.pageConversation(uid, versionId, Long.MAX_VALUE, count);
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
