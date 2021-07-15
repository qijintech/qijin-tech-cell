package tech.qijin.cell.user.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.user.db.model.UserImage;
import tech.qijin.cell.user.helper.CellUserImageHelper;
import tech.qijin.cell.user.service.CellUserImageService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class CellUserImageServiceImpl implements CellUserImageService {
    @Autowired
    private CellUserImageHelper cellUserImageHelper;

    @Override
    public List<UserImage> listUserImage(Long userId) {
        return cellUserImageHelper.listUserImage(userId);
    }

    @Override
    public Map<Long, List<UserImage>> mapUserImages(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds)) return Collections.emptyMap();
        return cellUserImageHelper.mapUserImages(userIds);
    }

    @Override
    public UserImage addImage(Long userId, String url) {
        UserImage userImage = new UserImage();
        userImage.setUserId(userId);
        userImage.setUrl(url);
        return cellUserImageHelper.insertUserImage(userImage);
    }

    @Override
    public boolean replaceImage(Long userId, Long id, String url) {
        return cellUserImageHelper.updateUserImageUrl(userId, id, url);
    }

    @Override
    public boolean deleteImage(Long userId, Long id) {
        return cellUserImageHelper.deleteUserImage(userId, id);
    }
}
