package tech.qijin.cell.im.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.base.ContentText;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.base.MsgType;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.helper.judge.CellImUserJudge;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.cell.im.service.impl.CellImMessageServiceImpl;
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
public class CellImMessageServiceTest extends BaseTest {
    @Autowired
    private CellImMessageServiceImpl imMessageService;
    @MockBean
    private CellImUserJudge cellImUserJudge;

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
        when(cellImUserJudge.doJudge(any())).thenReturn(
                new Judgement(Judgement.JudgementType.PASS));
        ImMessage imMessage = imMessageService.sendMessage(messageSendVo);
        log.info("imMessage={}", imMessage);
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
        when(cellImUserJudge.doJudge(any())).thenReturn(
                new Judgement(Constants.BuzCode.FORBIDDEN.getCode(),
                        Constants.BuzCode.FORBIDDEN.getMessage(),
                        Judgement.JudgementType.FORBIDDEN));
        ImMessage imMessage = imMessageService.sendMessage(messageSendVo);
        log.info("imMessage={}", imMessage);
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
        when(cellImUserJudge.doJudge(any())).thenReturn(
                new Judgement(Judgement.JudgementType.SILENT));
        ImMessage imMessage = imMessageService.sendMessage(messageSendVo);
        Assert.assertNotNull(imMessage);
        Assert.assertEquals((long) imMessage.getStatus(), 1L);
        log.info("imMessage={}", imMessage);

    }

    @Test
    public void testListHistoryMessage() {
        List<ImMessage> imMessages = imMessageService.listMessageHistory(uid, peerUid, 1590334661329000001L, 40);
        log.info("imMessages={}", imMessages);
    }

    @Test
    public void testListUnreadMessage() {
        List<ImMessage> imMessages = imMessageService.listMessageNew(uid, peerUid, 1590334661329000001L, 20);
        log.info("messageBOs={}", imMessages);
    }

    @Test
    public void testDelMessage() {
        // 先发送一条消息，再删除
        MessageSendVO messageSendVo = MessageSendVO.builder()
                .uid(uid)
                .toUid(peerUid)
                .msgType(MsgType.TEXT)
                .content(ContentText.builder().text(DateUtil.formatStr(DateUtil.now(), DateUtil.YYYYMMDDHHMMSS)).build())
                .build();
        when(cellImUserJudge.doJudge(any())).thenReturn(
                new Judgement(Judgement.JudgementType.PASS));
        ImMessage imMessage = imMessageService.sendMessage(messageSendVo);
        Assert.assertNotNull(imMessage);
        log.info("imMessage={}", imMessage);
        boolean res = imMessageService.delMessage(uid, peerUid, imMessage.getMsgId());
        Assert.assertTrue(res);
    }

    @Test
    public void testDelMessage2() {
        // 先发送一条消息，再删除
        MessageSendVO messageSendVo = MessageSendVO.builder()
                .uid(uid)
                .toUid(peerUid)
                .msgType(MsgType.TEXT)
                .content(ContentText.builder().text(DateUtil.formatStr(DateUtil.now(), DateUtil.YYYYMMDDHHMMSS)).build())
                .build();
        when(cellImUserJudge.doJudge(any())).thenReturn(
                new Judgement(Judgement.JudgementType.PASS));
        ImMessage imMessage = imMessageService.sendMessage(messageSendVo);
        Assert.assertNotNull(imMessage);
        log.info("imMessage={}", imMessage);
        boolean res = imMessageService.delMessage(uid, peerUid, imMessage.getMsgId());
        Assert.assertTrue(res);
        res = imMessageService.delMessage(peerUid, uid, imMessage.getMsgId());
        Assert.assertTrue(res);
    }
}
