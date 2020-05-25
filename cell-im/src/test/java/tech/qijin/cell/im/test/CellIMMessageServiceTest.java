package tech.qijin.cell.im.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.base.ContentText;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.base.MsgType;
import tech.qijin.cell.im.helper.judge.ImUserJudge;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.service.bo.MessageBO;
import tech.qijin.cell.im.service.impl.CellIMMessageServiceImpl;
import tech.qijin.util4j.lang.exception.ValidateException;
import tech.qijin.util4j.utils.DateUtil;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;


/**
 * @author michealyang
 * @date 2019-12-16
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class CellIMMessageServiceTest extends BaseTest {
    @Autowired
    private CellIMMessageServiceImpl imMessageService;
    @MockBean
    private ImUserJudge imUserJudge;

    public static long uid = 233333;
    public static long peerUid = 666666;

    @Test
    public void testSendMessage() {
        MessageSendVO messageSendVo = MessageSendVO.builder()
                .uid(233333L)
                .toUid(666666L)
                .msgType(MsgType.TEXT)
                .content(ContentText.builder().text(DateUtil.formatStr(DateUtil.now(), DateUtil.YYYYMMDDHHMMSS)).build())
                .build();
        when(imUserJudge.doJudge(any())).thenReturn(
                new Judgement(Judgement.JudgementType.PASS));
        MessageBO messageBO = imMessageService.sendMessage(messageSendVo);
        log.info("messageBO={}", messageBO);
    }

    /**
     * 测试禁止发送消息的场景
     */
    @Test(expected = ValidateException.class)
    public void testSendMessageForbidden() {
        MessageSendVO messageSendVo = MessageSendVO.builder()
                .uid(233333L)
                .toUid(666666L)
                .msgType(MsgType.TEXT)
                .content(ContentText.builder().text("hehe").build())
                .build();
        when(imUserJudge.doJudge(any())).thenReturn(
                new Judgement(Constants.BuzCode.FORBIDDEN.getCode(),
                        Constants.BuzCode.FORBIDDEN.getMessage(),
                        Judgement.JudgementType.FORBIDDEN));
        MessageBO messageBO = imMessageService.sendMessage(messageSendVo);
        log.info("messageBO={}", messageBO);
    }

    /**
     * 测试假发的逻辑
     */
    @Test()
    public void testSendMessageSilent() {
        MessageSendVO messageSendVo = MessageSendVO.builder()
                .uid(233333L)
                .toUid(666666L)
                .msgType(MsgType.TEXT)
                .content(ContentText.builder().text("hehe").build())
                .build();
        when(imUserJudge.doJudge(any())).thenReturn(
                new Judgement(Judgement.JudgementType.SILENT));
        MessageBO messageBO = imMessageService.sendMessage(messageSendVo);
        Assert.assertNotNull(messageBO);
        Assert.assertNotNull(messageBO.getImMessage());
        Assert.assertEquals((long) messageBO.getImMessage().getStatus(), 1L);
        log.info("messageBO={}", messageBO);

    }

    @Test
    public void testListHistoryMessage() {
        List<MessageBO> messageBOs = imMessageService.listHistoryMessage(uid, peerUid, 1590334661329000001L ,40);
        log.info("messageBOs={}", messageBOs);
    }

    @Test
    public void testListUnreadMessage() {
        List<MessageBO> messageBOs = imMessageService.listUnreadMessage(uid, peerUid, 1590334661329000001L, 20);
        log.info("messageBOs={}", messageBOs);
    }
}
