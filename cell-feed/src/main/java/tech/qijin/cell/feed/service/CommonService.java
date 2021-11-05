package tech.qijin.cell.feed.service;

import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.NumberUtil;

public class CommonService {
    private int defaultPageSize = 10;

    protected PageVo checkPage(PageVo pageVo, Integer customDefaultPageSize) {
        Integer pageSize = customDefaultPageSize == null ? defaultPageSize : customDefaultPageSize;
        if (pageVo == null) {
            return new PageVo(1, pageSize);
        }
        if (!NumberUtil.gtZero(pageVo.getPageNo())) {
            pageVo.setPageNo(1);
        }
        if (!NumberUtil.gtZero(pageVo.getPageSize())) {
            pageVo.setPageSize(pageSize);
        }
        return pageVo;
    }
}
