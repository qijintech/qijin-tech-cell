package tech.qijin.cell.misc.server.vo;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import tech.qijin.cell.misc.db.model.MiscUniversity;
import tech.qijin.util4j.utils.ConvertUtil;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UniversityVo {
    private Integer id;

    private String name;
    private String nameWithCountry;

    private String abbr;

    private String en;

    private String logo;

    private Long code;

    private String city;

    private String province;

    private String country;

    public static UniversityVo from(MiscUniversity university) {
        UniversityVo universityVo = ConvertUtil.convert(university, UniversityVo.class);
        if (universityVo != null) {
            universityVo.setNameWithCountry(String.format("%s-%s", university.getName(), university.getCountry()));
        }
        return universityVo;
    }

    public static List<UniversityVo> from(List<MiscUniversity> universities) {
        if (CollectionUtils.isEmpty(universities)) return Collections.emptyList();
        return universities.stream().map(UniversityVo::from).collect(Collectors.toList());
    }
}
