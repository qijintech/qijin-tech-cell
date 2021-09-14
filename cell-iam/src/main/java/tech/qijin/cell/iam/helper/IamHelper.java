package tech.qijin.cell.iam.helper;

import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;

import java.util.List;

public interface IamHelper {
    List<IamRole> listRole(Long userId);

    List<IamAuth> listAuth(Long userId);

    List<IamAuth> listAuthByRole(IamRole role);

    /**
     * 去重
     * @param roles
     * @return
     */
    List<IamAuth> listAuthByRoles(List<IamRole> roles);
}
