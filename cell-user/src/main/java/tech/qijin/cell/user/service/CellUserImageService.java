package tech.qijin.cell.user.service;

import tech.qijin.cell.user.db.model.UserImage;

import java.util.List;
import java.util.Map;

public interface CellUserImageService {
    List<UserImage> listUserImage(Long userId);

    Map<Long, List<UserImage>> mapUserImages(List<Long> userIds);

    UserImage addImage(Long userId, String url);
}
