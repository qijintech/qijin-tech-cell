package tech.qijin.cell.feed.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.db.dao.FeedCommentDao;
import tech.qijin.cell.feed.db.model.FeedComment;
import tech.qijin.cell.feed.db.model.FeedCommentExample;
import tech.qijin.cell.feed.helper.CellCommentHelper;
import tech.qijin.cell.feed.service.CellCommentService;
import tech.qijin.util4j.lang.vo.PageVo;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@Slf4j
@Service
public class CellCommentServiceImpl implements CellCommentService {
    private Integer defaultPageSize = 10;
    @Autowired
    private CellCommentHelper cellCommentHelper;

    @Override
    public Integer countComment(Long feedItemId, Long commentId) {
        return cellCommentHelper.countComment(feedItemId, commentId);
    }

    @Override
    public List<FeedComment> pageComment(Long feedItemId, Long commentId, PageVo pageVo) {
        pageVo = checkPage(pageVo);
        return cellCommentHelper.pageComment(feedItemId, commentId, pageVo.getPageNo(), pageVo.getPageSize());
    }

    private PageVo checkPage(PageVo pageVo) {
        if (pageVo == null) {
            return new PageVo(1, defaultPageSize);
        }
        if (!NumberUtil.gtZero(pageVo.getPageNo())) {
            pageVo.setPageNo(1);
        }
        if (!NumberUtil.gtZero(pageVo.getPageSize())) {
            pageVo.setPageSize(defaultPageSize);
        }
        return pageVo;
    }
}
