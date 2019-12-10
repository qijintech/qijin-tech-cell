package tech.qijin.cell.im.service;

import org.springframework.validation.annotation.Validated;
import tech.qijin.cell.im.service.bo.MessageBo;

import javax.validation.Valid;

/**
 * @author michealyang
 * @date 2019-11-04
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface ImMessageService {
    Object sendMessage(MessageBo messageBo);
}
