package tech.qijin.cell.im.helper.judge;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.MessageSendVO;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class ImJudgeChain {
    private List<IJudge> judges = Lists.newArrayList();

    @Autowired
    private ImUserJudge imUserJudge;
    @Autowired
    private ImTextJudge imTextJudge;
    @Autowired
    private ImImageJudge imImageJudge;

    @PostConstruct
    public void init() {
        judges.add(imUserJudge);
        judges.add(imTextJudge);
        judges.add(imImageJudge);
    }

    public Judgement doJudge(MessageSendVO messageVO) {
        for (IJudge judge : judges) {
            Judgement judgement = judge.doJudge(messageVO);
            if (judgement.getType() != Judgement.JudgementType.PASS) {
                return judgement;
            }
        }
        return Judgement.defaultJudgement();
    }
}
