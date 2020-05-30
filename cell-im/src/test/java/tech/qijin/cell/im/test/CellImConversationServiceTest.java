package tech.qijin.cell.im.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.service.CellImConversationService;

import java.util.List;

import static org.mockito.Mockito.any;


/**
 * @author michealyang
 * @date 2019-12-16
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class CellImConversationServiceTest extends BaseTest {
    @Autowired
    private CellImConversationService cellImConversationService;

    public static long uid = 233333;
    public static long peerUid = 666666;

    @Test
    public void testListConversationHistory() {
        List<ImConversation> imConversations = cellImConversationService.listConversationHistory(uid, 0L, 20);
        log.info("imConversations={}", imConversations);
    }
}
