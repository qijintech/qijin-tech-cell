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
    private CellImUserJudge cellImUserJudge;
    @Autowired
    private CellImTextJudge cellImTextJudge;
    @Autowired
    private CellImImageJudge cellImImageJudge;

    @PostConstruct
    public void init() {
        judges.add(cellImUserJudge);
        judges.add(cellImTextJudge);
        judges.add(cellImImageJudge);
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
