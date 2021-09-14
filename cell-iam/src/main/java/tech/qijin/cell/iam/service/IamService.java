package tech.qijin.cell.iam.service;

import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;

import java.util.List;

public interface IamService {

    List<IamRole> listRole(Long userId);


    /**
     * 判断用户是否拥有指定权限
     *
     * @param userId
     * @param iamAuth
     * @return
     */
    boolean hasAuth(Long userId, IamAuth iamAuth);

    boolean hasAuth(List<IamRole> roles, IamAuth iamAuth);
}
