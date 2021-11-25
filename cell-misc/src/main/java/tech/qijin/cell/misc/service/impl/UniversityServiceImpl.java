package tech.qijin.cell.misc.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.misc.db.model.MiscUniversity;
import tech.qijin.cell.misc.helper.UniversityHelper;
import tech.qijin.cell.misc.service.UniversityService;

import java.util.List;

@Slf4j
@Service
public class UniversityServiceImpl implements UniversityService {
    @Autowired
    private UniversityHelper universityHelper;

    @Override
    public List<MiscUniversity> searchUniversity(String keyword) {
        return universityHelper.searchByKeyword(keyword);
    }
}
