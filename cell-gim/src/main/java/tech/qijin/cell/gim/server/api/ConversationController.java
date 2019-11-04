package tech.qijin.cell.gim.server.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会话相关操作
 * <p>
 * 包含功能:
 * <ul>
 * <li>会话列表 - 按最新消息排序</li>
 * <li>删除会话</li>
 * <li>会话置顶 - Optional</li>
 * <li>会话免打扰 - Optional</li>
 * <li>清空聊天记录 - Optional</li>
 * </ul>
 * </p>
 *
 * @author michealyang
 * @date 2019-11-04
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/gim/conversation")
public class ConversationController {
}
