package tech.qijin.cell.im.test;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import tech.qijin.cell.im.base.*;
import tech.qijin.cell.im.base.MessageSendBo;
import tech.qijin.cell.im.db.model.ImMessage;
import tech.qijin.cell.im.service.CellImMessageService;
import tech.qijin.util4j.lang.exception.ValidateException;
import tech.qijin.util4j.utils.DateUtil;

import java.util.List;


/**
 * @author michealyang
 * @date 2019-12-16
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class CellImMessageServiceTest extends BaseTest {
    @Autowired
    private CellImMessageService cellImMessageService;

    public static long uid = 233333;
    public static long peerUid = 666666;

    @Test
    public void testSendMessage() {
        MessageSendBo messageSendBo = getMessageSendBo(MsgType.TEXT);
        ImMessage imMessage = cellImMessageService.sendMessage(messageSendBo);
        log.info("imMessage={}", imMessage);
    }

    /**
     * 测试禁止发送消息的场景
     */
    @Test(expected = ValidateException.class)
    public void testSendMessageForbidden() {
        MessageSendBo messageSendBo = getMessageSendBo(MsgType.TEXT);
        ImMessage imMessage = cellImMessageService.sendMessage(messageSendBo);
        log.info("imMessage={}", imMessage);
    }

    /**
     * 测试假发的逻辑
     */
    @Test()
    public void testSendMessageSilent() {
        MessageSendBo messageSendBo = getMessageSendBo(MsgType.TEXT);
        messageSendBo.setSilent(true);
        ImMessage imMessage = cellImMessageService.sendMessage(messageSendBo);
        Assert.assertNotNull(imMessage);
        Assert.assertEquals((long) imMessage.getStatus(), 1L);
        log.info("imMessage={}", imMessage);

    }

    @Test
    public void testListHistoryMessage() {
        List<CellImMessageBo> imMessages = cellImMessageService.listMessageHistory(uid, peerUid, 1590334661329000001L, 40);
        log.info("imMessages={}", imMessages);
    }

    @Test
    public void testListUnreadMessage() {
        List<CellImMessageBo> imMessages = cellImMessageService.listMessageNew(uid, peerUid, 1590334661329000001L, 20);
        log.info("messageBOs={}", imMessages);
    }

    @Test
    public void testDelMessage() {
        // 先发送一条消息，再删除
        MessageSendBo messageSendBo = getMessageSendBo(MsgType.TEXT);
        ImMessage imMessage = cellImMessageService.sendMessage(messageSendBo);
        Assert.assertNotNull(imMessage);
        log.info("imMessage={}", imMessage);
        boolean res = cellImMessageService.delMessage(uid, peerUid, imMessage.getMsgId());
        Assert.assertTrue(res);
    }

    @Test
    public void testDelMessage2() {
        // 先发送一条消息，再删除
        MessageSendBo messageSendBo = getMessageSendBo(MsgType.TEXT);
        ImMessage imMessage = cellImMessageService.sendMessage(messageSendBo);
        Assert.assertNotNull(imMessage);
        log.info("imMessage={}", imMessage);
        boolean res = cellImMessageService.delMessage(uid, peerUid, imMessage.getMsgId());
        Assert.assertTrue(res);
        res = cellImMessageService.delMessage(peerUid, uid, imMessage.getMsgId());
        Assert.assertTrue(res);
    }

    private MessageSendBo getMessageSendBo(MsgType msgType) {
        return MessageSendBo.builder()
                .uid(uid)
                .toUid(peerUid)
                .msgType(msgType)
                .content(getContent(msgType))
                .build();
    }

    private AbstractContent getContent(MsgType msgType) {
        switch (msgType) {
            case TEXT:
            default:
                ContentText contentText = new ContentText();
                contentText.setText(DateUtil.formatStr(DateUtil.now(), DateUtil.YYYYMMDDHHMMSS));
                return contentText;
        }
    }
}
