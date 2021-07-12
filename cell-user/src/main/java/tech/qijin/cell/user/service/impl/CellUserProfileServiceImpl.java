package tech.qijin.cell.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.db.dao.UserProfileDao;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.db.model.UserProfileExample;
import tech.qijin.cell.user.service.CellUserProfileService;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellUserProfileServiceImpl implements CellUserProfileService {
    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public void updateProfile(UserProfile profile) {
        MAssert.isTrue(profile != null && NumberUtil.gtZero(profile.getUserId()),
                ResEnum.INVALID_PARAM);
        UserProfileExample example = new UserProfileExample();
        example.createCriteria()
                .andUserIdEqualTo(profile.getUserId());
        userProfileDao.updateByExampleSelective(profile, example);
    }

    @Override
    public UserProfile getProfile(Long userId) {
        MAssert.isTrue(NumberUtil.gtZero(userId), ResEnum.INVALID_PARAM);
        UserProfileExample example = new UserProfileExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return userProfileDao.selectByExample(example).stream().findFirst().orElse(null);
    }

    @Override
    public Map<Long, UserProfile> mapProfile(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) return Collections.emptyMap();
        UserProfileExample example = new UserProfileExample();
        example.createCriteria()
                .andUserIdIn(userIds);
        return userProfileDao.selectByExample(example)
                .stream()
                .collect(Collectors.toMap(UserProfile::getUserId, Function.identity()));
    }
}
