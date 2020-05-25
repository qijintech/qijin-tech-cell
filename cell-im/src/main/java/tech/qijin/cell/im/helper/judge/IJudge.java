package tech.qijin.cell.im.helper.judge;

import tech.qijin.cell.im.base.MessageSendVO;

public interface IJudge {
    Judgement doJudge(MessageSendVO messageVO);
}
