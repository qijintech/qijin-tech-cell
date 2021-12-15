package tech.qijin.cell.counting.helper;

import tech.qijin.cell.counting.db.model.CountingTemplate;

import java.util.List;
import java.util.Map;

public interface CellCountingHelper {
    /**
     * 根据countingCode查询技术模板
     * @param countingCode
     * @return
     */
    CountingTemplate getTemplateByCode(String countingCode);
    /**
     * 获取所有计数模板
     * @return
     */
    List<CountingTemplate> listAllTemplates();

    /**
     * 查询event和技术模板的映射关系，即哪些计数是关联对应event的
     * @return
     */
    Map<String, List<CountingTemplate>> mapEventTemplates();
}
