package tech.qijin.cell.im.helper.judge;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.base.Constants;
import tech.qijin.cell.im.base.MessageSendVO;

import java.util.List;

@Slf4j
@Service
public class CellImUserJudge implements IJudge {
    @Autowired
    private List<IUserJudge> userJudgeChain = Lists.newArrayList();
    @Autowired
    private ImStatusJudge imStatusJudge;
    @Autowired
    private ImRelationJudge imRelationJudge;

    @Override

    public Judgement doJudge(MessageSendVO messageVO) {

        // 如有修改，只需增删 userJudgeChain 里面的元素即可
        if (CollectionUtils.isEmpty(userJudgeChain)) {
            // 检查用户状态
            userJudgeChain.add(imStatusJudge);
            // 检查好友关系
            userJudgeChain.add(imRelationJudge);
        }


        for (IUserJudge userJudge : userJudgeChain) {
            Judgement judgement = userJudge.doJudge(messageVO.getUid(), messageVO.getToUid());
            if (judgement.getType() != Judgement.JudgementType.PASS) {
                log.error("");
                return judgement;
            }
        }
        return Judgement.defaultJudgement();
    }

    /**
     * 用户判断
     */
    interface IUserJudge {
        Judgement doJudge(Long uid, Long peerUid);
    }

    /**
     * 判断用户状态
     */
    @Component
    public static class ImStatusJudge implements IUserJudge {
        @Override
        public Judgement doJudge(Long uid, Long peerUid) {
//            return new Judgement(Constants.BuzCode.FORBIDDEN.getCode(),
//                    Constants.BuzCode.FORBIDDEN.getMessage(),
//                    Judgement.JudgementType.FORBIDDEN);
            return Judgement.defaultJudgement();
        }
    }

    /**
     * 判断好友关系
     */
    @Component
    public static class ImRelationJudge implements IUserJudge {
        @Override
        public Judgement doJudge(Long uid, Long peerUid) {
            return Judgement.defaultJudgement();
        }
    }
}
