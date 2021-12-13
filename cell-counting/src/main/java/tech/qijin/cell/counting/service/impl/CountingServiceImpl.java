package tech.qijin.cell.counting.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import tech.qijin.cell.counting.db.model.CountingRecord;
import tech.qijin.cell.counting.db.model.CountingTemplate;
import tech.qijin.cell.counting.helper.CountingHelper;
import tech.qijin.cell.counting.helper.CountingRecordHelper;
import tech.qijin.cell.counting.service.CountingService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.lang.event.EventBase;
import tech.qijin.util4j.utils.DateUtil;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CountingServiceImpl implements CountingService {
    @Autowired
    private CountingHelper countingHelper;
    @Autowired
    private CountingRecordHelper countingRecordHelper;

    @Override
    public void onEvent(EventBase event) {
        checkEvent(event);
        Map<String, List<CountingTemplate>> eventTemplatesMap = countingHelper.mapEventTemplates();
        if (MapUtils.isEmpty(eventTemplatesMap)) {
            log.warn("CountingService unbound event, event={}", event);
            return;
        }
        List<CountingTemplate> templates = eventTemplatesMap.get(event.getKind().name());
        if (CollectionUtils.isEmpty(templates)) {
            log.warn("CountingService unbound event , event={}", event);
            return;
        }
        templates.stream().forEach(template -> {
            //
        });
    }

    @Override
    public void register(Long userId, String countingCode) {

    }

    @Override
    public void reset(Long userId, String countingCode) {

    }

    @Retryable(value = {Exception.class}, backoff = @Backoff(delay = 10L, multiplier = 2.0))
    public void incr(Long userId, EventBase event, CountingTemplate template) {
        String countingTemplate = getCountingFormat(userId, template);
        if (StringUtils.isBlank(countingTemplate)) {
            log.error("CountingService invalid countingTemplate={}", countingTemplate);
            return;
        }
        CountingRecord record = countingRecordHelper.getRecordByCountingFormat(countingTemplate);
        if (record == null) {
            record = new CountingRecord();
            record.setCountingCode(template.getCode());
            record.setUserId(userId);
            record.setCountingFormat(countingTemplate);
            record.setTarget(template.getTarget());
            if (checkTarget(event, template, record)) {
                // TODO trigger
            }
            if (!countingRecordHelper.insertRecord(record)) {
                log.error("CountingService insert record fail, record={}", JSON.toJSONString(record));
                return;
            }

        } else {
            if (isAlreadyOnTarget(record)) {
                return;
            }
            if (checkTarget(event, template, record)) {
                // TODO trigger
            }
            if (!countingRecordHelper.updateRecord(record)) {
                log.error("CountingService insert record fail, record={}", JSON.toJSONString(record));
                return;
            }
        }
    }

    private String getCountingFormat(Long userId, CountingTemplate template) {
        Date now = DateUtil.now();
        switch (template.getMode()) {
            case ACCUMULATE:
                return String.format("counting:accumulate:%s:%d", template.getCode(), userId);
            case DAILY:
                return String.format("counting:daily:%s:%d:%s", template.getCode(), userId, DateUtil.formatStr(now, "yyyyMMdd"));
            case WEEKLY:
                return String.format("counting:weekly:%s:%d:%s", template.getCode(), userId, DateUtil.getCurrentWeek());
            case MONTHLY:
                return String.format("counting:monthly:%s:%d:%s", template.getCode(), userId, DateUtil.formatStr(now, "yyyyMM"));
            case YEARLY:
                return String.format("counting:yearly:%s:%d:%s", template.getCode(), userId, DateUtil.formatStr(now, "yyyy"));
            default:
                return "";
        }
    }

    private boolean isAlreadyOnTarget(CountingRecord record) {
        return record.getCurr().compareTo(record.getTarget()) >= 0L;
    }

    private boolean checkTarget(EventBase event, CountingTemplate template, CountingRecord record) {
        Long curr = event.getDelta() + (record.getCurr() == null ? 0L : record.getCurr());
        boolean isExceed = curr.compareTo(record.getTarget()) >= 0L;
        if (!isExceed) {
            record.setCurr(curr);
            return false;
        }
        switch (template.getOnTargetMode()) {
            case END:
                record.setCurr(record.getTarget());
                break;
            case CYCLE:
                record.setCurr(0L);
                break;
            default:
                throw new UnsupportedOperationException();
        }
        return true;
    }

    private void checkEvent(EventBase event) {
        MAssert.notNull(event, ResEnum.INVALID_PARAM);
        MAssert.isTrue(NumberUtil.gtZero(event.getUserId()), ResEnum.INVALID_PARAM);
        if (!NumberUtil.gtZero(event.getDelta())) {
            event.setDelta(1L);
        }
    }
}
