package tech.qijin.cell.iam.helper;

import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;
import tech.qijin.cell.iam.db.model.UserRole;

import java.util.List;

public interface IamHelper {
    List<UserRole> listUserRole(Long userId);

    List<IamRole> listRole(Long userId, Long dataId);

    List<IamAuth> listAuth(Long userId, Long dataId);

    List<IamAuth> listAuthByRole(IamRole role);

    List<IamRole> listRoleByAuth(IamAuth auth);

    List<UserRole> listUserIdByRoleAndData(Long dataId, List<IamRole> roles);

    /**
     * 去重
     *
     * @param roles
     * @return
     */
    List<IamAuth> listAuthByRoles(List<IamRole> roles);

    boolean addRole(Long userId, Long dataId, IamRole iamRole);
    boolean rmRole(Long userId, Long dataId, IamRole iamRole);
}
