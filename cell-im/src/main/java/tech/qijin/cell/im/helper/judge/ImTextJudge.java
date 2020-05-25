package tech.qijin.cell.im.helper.judge;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.base.MsgType;

/**
 * 判断是否有敏感词
 */
@Service
public class ImTextJudge implements IJudge {
    @Override
    public Judgement doJudge(MessageSendVO messageVO) {
        if (messageVO.getMsgType() != MsgType.TEXT) {
            return Judgement.defaultJudgement();
        }
        // TODO
        return Judgement.defaultJudgement();
    }
}
