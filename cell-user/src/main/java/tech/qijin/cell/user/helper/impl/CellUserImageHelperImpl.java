package tech.qijin.cell.user.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.base.ImageStatus;
import tech.qijin.cell.user.db.dao.UserImageDao;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.db.model.UserImageExample;
import tech.qijin.cell.user.helper.CellUserImageHelper;
import tech.qijin.util4j.lang.constant.ResEnum;
import tech.qijin.util4j.utils.MAssert;
import tech.qijin.util4j.utils.NumberUtil;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CellUserImageHelperImpl implements CellUserImageHelper {
    @Autowired
    private UserImageDao userImageDao;

    @Override
    public List<UserImage> listUserImage(Long userId) {
        UserImageExample example = new UserImageExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(ImageStatus.SHOW);
        return userImageDao.selectByExample(example);
    }

    @Override
    public Map<Long, List<UserImage>> mapUserImages(List<Long> userIds) {
        UserImageExample example = new UserImageExample();
        example.createCriteria()
                .andUserIdIn(userIds)
                .andStatusEqualTo(ImageStatus.SHOW);
        return userImageDao.selectByExample(example)
                .stream()
                .collect(Collectors.groupingBy(UserImage::getUserId));
    }

    @Override
    public UserImage insertUserImage(UserImage userImage) {
        MAssert.isTrue(NumberUtil.gtZero(userImage.getUserId()) && StringUtils.isNotBlank(userImage.getUrl()),
                ResEnum.INVALID_PARAM);
        if (userImage.getStatus() == null) {
            userImage.setStatus(ImageStatus.SHOW);
        }
        userImageDao.insertSelective(userImage);
        return userImage;
    }

    @Override
    public boolean updateUserImageUrl(Long userId, Long id, String url) {
        UserImageExample example = new UserImageExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(userId);
        UserImage record = new UserImage();
        record.setUrl(url);
        return userImageDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public boolean deleteUserImage(Long userId, Long id) {
        UserImageExample example = new UserImageExample();
        example.createCriteria()
                .andIdEqualTo(id)
                .andUserIdEqualTo(userId);
        UserImage record = new UserImage();
        record.setStatus(ImageStatus.HIDE);
        return userImageDao.updateByExampleSelective(record, example) > 0;
    }

    @Override
    public UserImage getFirstImage(Long userId) {
        UserImageExample example = new UserImageExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andStatusEqualTo(ImageStatus.SHOW);
        return userImageDao.selectByExample(example)
                .stream()
                .min(Comparator.comparingLong(UserImage::getId))
                .orElse(null);
    }
}
