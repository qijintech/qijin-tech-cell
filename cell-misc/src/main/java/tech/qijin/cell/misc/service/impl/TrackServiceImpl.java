package tech.qijin.cell.misc.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.misc.db.model.MiscTrack;
import tech.qijin.cell.misc.helper.TrackHelper;
import tech.qijin.cell.misc.service.TrackService;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.web.util.UserUtil;

@Slf4j
@Service
public class TrackServiceImpl implements TrackService {
    @Autowired
    private TrackHelper trackHelper;

    @Override
    public boolean addTrack(String event, Object data) {
        MAssert.checkParam(StringUtils.isNotBlank(event), "invalid event");
        MAssert.checkParam(data != null, "invalid data");
        MiscTrack track = new MiscTrack();
        track.setOperator(UserUtil.getUserId());
        track.setEvent(event);
        track.setData(JSON.toJSONString(data));
        return trackHelper.addTrack(track);
    }
}
