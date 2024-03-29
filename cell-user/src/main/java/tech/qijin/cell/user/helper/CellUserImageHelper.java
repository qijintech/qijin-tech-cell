package tech.qijin.cell.user.helper;

import tech.qijin.cell.user.db.model.UserImage;

import java.util.List;
import java.util.Map;

public interface CellUserImageHelper {
    List<UserImage> listUserImage(Long userId);

    UserImage insertUserImage(UserImage userImage);

    Map<Long, List<UserImage>> mapUserImages(List<Long> userIds);

    boolean updateUserImageUrl(Long userId, Long id, String url);

    boolean deleteUserImage(Long userId, Long id);

    UserImage getFirstImage(Long userId);
}
