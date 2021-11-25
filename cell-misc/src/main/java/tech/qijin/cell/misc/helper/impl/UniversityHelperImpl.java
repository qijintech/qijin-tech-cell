package tech.qijin.cell.misc.helper.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.misc.db.dao.MiscUniversityDao;
import tech.qijin.cell.misc.db.model.MiscUniversity;
import tech.qijin.cell.misc.db.model.MiscUniversityExample;
import tech.qijin.cell.misc.helper.UniversityHelper;
import tech.qijin.util4j.trace.pojo.Channel;
import tech.qijin.util4j.trace.util.ChannelUtil;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UniversityHelperImpl implements UniversityHelper {
    private List<MiscUniversity> universityCache = Lists.newArrayList();
    @Autowired
    private MiscUniversityDao miscUniversityDao;

    @PostConstruct
    public void init() {
        ChannelUtil.setChannel(Channel.NONE);
        universityCache = listAllUniversity();
    }

    @Override
    public List<MiscUniversity> searchByKeyword(String keyword) {
        return universityCache.stream().filter(university -> university.getName().contains(keyword)).collect(Collectors.toList());
    }

    private List<MiscUniversity> listAllUniversity(){
        MiscUniversityExample example = new MiscUniversityExample();
        return miscUniversityDao.selectByExample(example);
    }
}
