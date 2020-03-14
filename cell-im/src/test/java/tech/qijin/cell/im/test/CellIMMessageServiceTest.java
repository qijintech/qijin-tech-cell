package tech.qijin.cell.im.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.cell.im.base.ContentText;
import tech.qijin.cell.im.base.MessageSendVo;
import tech.qijin.cell.im.service.impl.CellIMMessageServiceImpl;

/**
 * @author michealyang
 * @date 2019-12-16
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class CellIMMessageServiceTest extends BaseTest {
    @Autowired
    private CellIMMessageServiceImpl imMessageService;

    @Test
    public void testSendMessage() {
        MessageSendVo messageSendVo = MessageSendVo.builder()
                .content(ContentText.builder().text("hehe").build())
                .build();
        Long msgId = imMessageService.sendMessage(messageSendVo);
        log.info("msgId={}", msgId);
    }
}
