package tech.qijin.cell.misc.server.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.misc.server.vo.TrackVo;
import tech.qijin.cell.misc.service.TrackService;
import tech.qijin.util4j.lang.annotation.FreeAccess;
import tech.qijin.util4j.web.pojo.ResultVo;

@RestController
@RequestMapping("/api/v1/misc/track")
public class TrackController {
    @Autowired
    private TrackService trackService;

    @FreeAccess
    @PostMapping("/add")
    public ResultVo search(@RequestBody TrackVo track) {
        trackService.addTrack(track.getEvent(), track.getData());
        return ResultVo.instance().success();
    }
}
