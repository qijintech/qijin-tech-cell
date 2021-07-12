package tech.qijin.cell.user.service;

import tech.qijin.cell.user.db.model.UserProfile;

import java.util.List;
import java.util.Map;

/**
 * @author michealyang
 * @date 2019-12-10
 * @relax: 开始眼保健操 ←_← ↓_↓ →_→ ↑_↑
 */
public interface CellUserProfileService {

    void updateProfile(UserProfile profile);

    UserProfile getProfile(Long userId);

    Map<Long, UserProfile> mapProfile(List<Long> userIds);
}
