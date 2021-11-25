package tech.qijin.cell.misc.server.api;

import com.sun.tools.hat.internal.util.Misc;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.qijin.cell.misc.db.model.MiscUniversity;
import tech.qijin.cell.misc.server.vo.UniversitiesVo;
import tech.qijin.cell.misc.server.vo.UniversityVo;
import tech.qijin.cell.misc.service.UniversityService;
import tech.qijin.util4j.lang.annotation.FreeAccess;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@RestController
@RequestMapping("/api/v1/misc/country")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @FreeAccess
    @GetMapping("/search")
    public UniversitiesVo search(String keyword, Integer size) {
        if (StringUtils.isBlank(keyword)){
            return UniversitiesVo.builder().build();
        }
        List<MiscUniversity> universities = universityService.searchUniversity(keyword);
        return UniversitiesVo.builder()
                .universities(UniversityVo.from(truncate(universities, size)))
                .build();
    }

    private List<MiscUniversity> truncate(List<MiscUniversity> universities, Integer size) {
        if (CollectionUtils.isEmpty(universities)) return universities;
        if (!NumberUtil.gtZero(size)){
            size = 20;
        }
        Integer limit = Math.min(universities.size(), size);
        return universities.subList(0, limit);
    }
}
