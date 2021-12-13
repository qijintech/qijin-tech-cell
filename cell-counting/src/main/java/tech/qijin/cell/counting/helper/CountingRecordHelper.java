package tech.qijin.cell.counting.helper;

import tech.qijin.cell.counting.db.model.CountingRecord;

public interface CountingRecordHelper {
    CountingRecord getRecordByCountingFormat(String countingFormat);

    boolean insertRecord(CountingRecord record);

    boolean updateRecord(CountingRecord record);
}
