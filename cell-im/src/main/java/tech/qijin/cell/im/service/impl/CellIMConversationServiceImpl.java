package tech.qijin.cell.im.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.service.CellIMConversationService;
import tech.qijin.cell.im.service.bo.MessageBO;

/**
 * @author michealyang
 * @date 2019-12-19
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class CellIMConversationServiceImpl implements CellIMConversationService {
    @Override
    public boolean deleteConversation(Long uid, Long peerUid) {
        return false;
    }
}
