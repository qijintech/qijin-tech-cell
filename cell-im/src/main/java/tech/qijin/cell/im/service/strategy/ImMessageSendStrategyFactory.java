package tech.qijin.cell.im.service.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.im.helper.judge.Judgement;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;

@Service
public class ImMessageSendStrategyFactory {
    @Autowired
    private ImMessageSendNormalStrategy imMessageSendNormalStrategy;
    @Autowired
    private ImMessageSendSilentStrategy imMessageSendSilentStrategy;
    @Autowired
    private ImMessageSendForbiddenStrategy imMessageSendForbiddenStrategy;

    public IMessageSendStrategy getStrategy(Judgement judgement) {
        switch (judgement.getType()) {
            case PASS:
                return imMessageSendNormalStrategy;
            case SILENT:
                return imMessageSendSilentStrategy;
            case FORBIDDEN:
                return imMessageSendForbiddenStrategy;
        }
        MAssert.isTrue(false, ResEnum.INTERNAL_ERROR, "unsupported strategy");
        return null;
    }
}
