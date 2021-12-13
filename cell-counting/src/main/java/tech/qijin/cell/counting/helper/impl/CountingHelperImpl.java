package tech.qijin.cell.counting.helper.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tech.qijin.cell.counting.db.dao.CountingTemplateDao;
import tech.qijin.cell.counting.db.model.CountingTemplate;
import tech.qijin.cell.counting.db.model.CountingTemplateExample;
import tech.qijin.cell.counting.helper.CountingHelper;
import tech.qijin.util4j.lang.event.EventBase;
import tech.qijin.util4j.utils.ConvertUtil;
import tech.qijin.util4j.utils.DateUtil;

import javax.annotation.PostConstruct;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CountingHelperImpl implements CountingHelper {
    private Date lastUpdateAt = Date.from(Instant.EPOCH);
    private List<CountingTemplate> templates = Lists.newArrayList();
    private Map<String, List<CountingTemplate>> eventCountingMap = Maps.newHashMap();

    @Autowired
    private CountingTemplateDao countingTemplateDao;


    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void reload() {
        Date updateAt = countingTemplateDao.getLastUpdatedAt();
        if (null == updateAt
                || updateAt.after(lastUpdateAt)
                || Math.abs(DateUtil.getMinusSeconds(DateUtil.now(), updateAt).longValue()) < 10) {
            templates = listAllTemplates();
            eventCountingMap = templates.stream().collect(Collectors.groupingBy(template -> template.getEvent().getKind().name()));
            lastUpdateAt = updateAt;
        }
    }

    @Override
    public List<CountingTemplate> listAllTemplates() {
        if (CollectionUtils.isNotEmpty(templates)) return templates;
        return listAllTemplatesFromDB();
    }

    @Override
    public Map<String, List<CountingTemplate>> mapEventTemplates() {
        return eventCountingMap;
    }

    public List<CountingTemplate> listAllTemplatesFromDB() {
        CountingTemplateExample example = new CountingTemplateExample();
        return countingTemplateDao.selectByExample(example);
    }
}
