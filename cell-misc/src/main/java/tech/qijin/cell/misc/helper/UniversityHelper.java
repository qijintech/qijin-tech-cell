package tech.qijin.cell.misc.helper;

import tech.qijin.cell.misc.db.model.MiscUniversity;

import java.util.List;

public interface UniversityHelper {
    List<MiscUniversity> searchByKeyword(String keyword);
}
