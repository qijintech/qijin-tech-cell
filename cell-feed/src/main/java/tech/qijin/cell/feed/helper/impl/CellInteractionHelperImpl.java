package tech.qijin.cell.feed.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.feed.base.FeedInteractionKind;
import tech.qijin.cell.feed.db.dao.FeedInteractionDao;
import tech.qijin.cell.feed.db.model.FeedInteraction;
import tech.qijin.cell.feed.db.model.FeedInteractionExample;
import tech.qijin.cell.feed.helper.CellInteractionHelper;
import tech.qijin.cell.feed.helper.CommonHelper;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.List;

@Slf4j
@Service
public class CellInteractionHelperImpl extends CommonHelper implements CellInteractionHelper {
    @Autowired
    private FeedInteractionDao feedInteractionDao;

    @Override
    public FeedInteraction getInteraction(String format) {
        FeedInteractionExample example = new FeedInteractionExample();
        example.createCriteria()
                .andFormatEqualTo(format);
        return feedInteractionDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public FeedInteraction getInteraction(Long feedId, Long fromUserId, FeedInteractionKind kind, Long commentId) {
        return null;
    }

    @Override
    public boolean addInteraction(FeedInteraction interaction) {
        String res = checkInteraction(interaction);
        if (StringUtils.isNotBlank(res)) {
            log.error("addInteraction invalid param, res={}", res);
            return false;
        }
        return feedInteractionDao.insertSelective(interaction) > 0;
    }

    @Override
    public List<FeedInteraction> pageInteraction(Long userId, Integer pageNo, Integer pageSize) {
        FeedInteractionExample example = new FeedInteractionExample();
        example.setOrderByClause(orderBy("createTime", "desc", pageNo, pageSize));
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return feedInteractionDao.selectByExample(example);
    }

    @Override
    public boolean updateInteractionValid(Long id, boolean valid) {
        FeedInteraction update = new FeedInteraction();
        update.setId(id);
        update.setValid(valid);
        return feedInteractionDao.updateByPrimaryKeySelective(update) > 0;
    }

    private String checkInteraction(FeedInteraction interaction) {
        if (!NumberUtil.gtZero(interaction.getUserId())) return "userId";
        if (!NumberUtil.gtZero(interaction.getFromUserId())) return "fromUserId";
        if (!NumberUtil.gtZero(interaction.getFeedId())) return "feedId";
        if (interaction.getKind() == null) return "kind";
        if (StringUtils.isBlank(interaction.getFormat())) return "format";
        return "";
    }
}
