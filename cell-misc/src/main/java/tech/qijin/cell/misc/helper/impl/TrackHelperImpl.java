package tech.qijin.cell.misc.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.misc.db.dao.MiscTrackDao;
import tech.qijin.cell.misc.db.model.MiscTrack;
import tech.qijin.cell.misc.helper.TrackHelper;
import tech.qijin.util4j.utils.MAssert;

@Slf4j
@Service
public class TrackHelperImpl implements TrackHelper {
    @Autowired
    private MiscTrackDao miscTrackDao;

    @Override
    public boolean addTrack(MiscTrack track) {
        return miscTrackDao.insertSelective(track) > 0;
    }
}
