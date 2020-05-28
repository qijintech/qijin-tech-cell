package tech.qijin.cell.im.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.base.ContentText;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.base.MsgType;
import tech.qijin.cell.im.db.model.ImConversation;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.judge.CellImUserJudge;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.service.CellImConversationService;
import tech.qijin.cell.im.service.CellImMessageService;
import tech.qijin.cell.im.service.impl.CellImMessageServiceImpl;
import tech.qijin.util4j.lang.exception.ValidateException;
import tech.qijin.util4j.utils.DateUtil;

import java.util.List;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


/**
 * @author michealyang
 * @date 2019-12-16
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class CellImConversationServiceTest extends BaseTest {
    @Autowired
    private CellImConversationService cellImConversationService;
    @MockBean
    private CellImUserJudge cellImUserJudge;

    public static long uid = 233333;
    public static long peerUid = 666666;

    @Test
    public void testListConversationHistory() {
        List<ImConversation> imConversations = cellImConversationService.listConversationHistory(uid, 0L, 20);
        log.info("imConversations={}", imConversations);
    }
}
