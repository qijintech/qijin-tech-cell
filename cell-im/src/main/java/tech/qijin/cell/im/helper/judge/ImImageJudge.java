package tech.qijin.cell.im.helper.judge;

import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;
import tech.qijin.cell.im.base.MsgType;

@Service
public class ImImageJudge implements IJudge{
    @Override
    public Judgement doJudge(MessageSendVO messageVO) {
        if (messageVO.getMsgType() != MsgType.IMG) {
            return Judgement.defaultJudgement();
        }
        // TODO
        return Judgement.defaultJudgement();
    }
}
