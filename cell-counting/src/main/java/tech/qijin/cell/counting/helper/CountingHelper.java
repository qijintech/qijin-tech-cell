package tech.qijin.cell.counting.helper;

import tech.qijin.cell.counting.db.model.CountingTemplate;

import java.util.List;
import java.util.Map;

public interface CountingHelper {
    List<CountingTemplate> listAllTemplates();

    Map<String, List<CountingTemplate>> mapEventTemplates();
}
