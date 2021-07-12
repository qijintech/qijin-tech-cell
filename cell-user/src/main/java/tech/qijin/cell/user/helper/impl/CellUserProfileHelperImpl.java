package tech.qijin.cell.user.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.db.dao.UserProfileDao;
import tech.qijin.cell.user.db.model.UserProfile;
import tech.qijin.cell.user.helper.CellUserProfileHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.LogFormat;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

@Slf4j
@Service
public class CellUserProfileHelperImpl implements CellUserProfileHelper {
    @Autowired
    private UserProfileDao userProfileDao;

    @Override
    public UserProfile createProfile(UserProfile profile) {
        MAssert.isTrue(NumberUtil.gtZero(profile.getUserId()), ResEnum.INVALID_PARAM);
        try {
            userProfileDao.insertSelective(profile);
        } catch (DuplicateKeyException e) {
            log.error(LogFormat.builder()
                    .message("duplicate userId")
                    .put("userId", profile.getUserId())
                    .build());
        }
        return profile;
    }
}
