package tech.qijin.cell.counting.helper.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.counting.db.dao.CountingRecordDao;
import tech.qijin.cell.counting.db.model.CountingRecord;
import tech.qijin.cell.counting.db.model.CountingRecordExample;
import tech.qijin.cell.counting.helper.CellCountingRecordHelper;
import tech.qijin.util4j.utils.NumberUtil;

@Slf4j
@Service
public class CellCountingRecordHelperImpl implements CellCountingRecordHelper {
    @Autowired
    private CountingRecordDao countingRecordDao;

    @Override
    public CountingRecord getRecordByCountingFormat(String countingFormat) {
        CountingRecordExample example = new CountingRecordExample();
        example.createCriteria()
                .andCountingFormatEqualTo(countingFormat);
        return countingRecordDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public boolean insertRecord(CountingRecord record) {
        if (!checkRecord(record)) {
            log.error("CountingRecordHelper invalid record={}", JSON.toJSONString(record));
            return false;
        }
        return countingRecordDao.insertSelective(record) > 0;
    }

    @Override
    public boolean updateRecord(CountingRecord record) {
        CountingRecord update = new CountingRecord();
        update.setCurr(record.getCurr());
        update.setVersion(record.getVersion() + 1);

        CountingRecordExample example = new CountingRecordExample();
        example.createCriteria()
                .andIdEqualTo(record.getId())
                .andVersionEqualTo(record.getVersion());
        return countingRecordDao.updateByExampleSelective(update, example) > 0;
    }

    public boolean checkRecord(CountingRecord record) {
        if (record == null) return false;
        if (!NumberUtil.gtZero(record.getUserId())) return false;
        if (StringUtils.isBlank(record.getCountingFormat())) return false;
        if (StringUtils.isBlank(record.getCountingCode())) return false;
        if (!NumberUtil.gtZero(record.getTarget())) return false;
        return true;
    }
}
