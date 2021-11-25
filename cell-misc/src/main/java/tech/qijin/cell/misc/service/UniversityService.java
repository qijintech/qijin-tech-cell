package tech.qijin.cell.misc.service;

import tech.qijin.cell.misc.db.model.MiscUniversity;

import java.util.List;

public interface UniversityService {
    List<MiscUniversity> searchUniversity(String keyword);
}
