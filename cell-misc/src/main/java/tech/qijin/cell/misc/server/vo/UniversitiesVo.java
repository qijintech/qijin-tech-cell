package tech.qijin.cell.misc.server.vo;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UniversitiesVo {
    private List<UniversityVo> universities;
}
