package tech.qijin.cell.im.test;

import com.alibaba.fastjson.JSON;
import com.google.common.hash.Hashing;
import tech.qijin.cell.im.base.MsgType;
import tech.qijin.cell.im.service.bo.*;
import tech.qijin.util4j.utils.DateUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author michealyang
 * @date 2019-11-06
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public class Test {
    @org.junit.Test
    public void test() throws IOException {
        MessageBo messageBo = new MessageBo();
        messageBo.setConversationId(DateUtil.now().getTime());
        messageBo.setToUid(123456L);

        Content content = new ContentText();
        messageBo.setMsgType(MsgType.TEXT);
        ((ContentText)content).setText("hello");

        content = new ContentAudio();
        messageBo.setMsgType(MsgType.AUDIO);
        ((ContentAudio)content).setUrl("http://123.com/abc");
        ((ContentAudio)content).setDuration(10);

        content = new ContentImg();
        messageBo.setMsgType(MsgType.IMG);
        ((ContentImg)content).setUrl("http://123.com/img");

        content = new ContentLocation();
        messageBo.setMsgType(MsgType.LOCATION);
        ((ContentLocation)content).setLng("119.23456");
        ((ContentLocation)content).setLat("45.2345");
        ((ContentLocation)content).setAddress("望京");

        content = new ContentSticker();
        messageBo.setMsgType(MsgType.STICKER);
        ((ContentSticker)content).setUrl("http://123.com/sticker");

        content.setExt("这里设置扩展信息");

        messageBo.setContent(content);
        System.out.printf(JSON.toJSONString(messageBo));
    }
}
