package tech.qijin.cell.im.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.service.ImMessageService;
import tech.qijin.cell.im.service.bo.MessageBo;

/**
 * @author michealyang
 * @date 2019-11-08
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@Service
public class ImMessageServiceImpl implements ImMessageService {
    @Override
    public Object sendMessage(MessageBo messageBo) {
        // validation
        // gen message id
        // insert data
        // adjust unread
        // push
        return null;
    }
}
