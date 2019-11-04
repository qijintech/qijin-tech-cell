package tech.qijin.cell.gim.server.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 消息相关操作
 * <p>
 * 包含功能:
 * <ul>
 * <li>发送消息</li>
 * <li>拉取最新消息</li>
 * <li>拉取历史消息</li>
 * <li>撤回消息 - Optional</li>
 * <li>删除消息 - Optional</li>
 * <li>已读回执 - Optional</li>
 * <li>消息转发 - Optional</li>
 * <li>消息引用 - Optional</li>
 * </ul>
 * </p>
 *
 * @author michealyang
 * @date 2019-11-04
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/gim/message")
public class MessageController {
}
