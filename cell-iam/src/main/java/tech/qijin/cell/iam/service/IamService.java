package tech.qijin.cell.iam.service;

import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;

import java.util.List;

public interface IamService {

    List<IamRole> listRole(Long userId, Long dataId);


    /**
     * 判断用户是否拥有指定权限
     *
     * @param userId
     * @param iamAuth
     * @return
     */
    boolean hasAuth(Long userId, Long dataId, IamAuth iamAuth);

    boolean hasAuth(Long userId, IamAuth iamAuth);

    boolean hasAuth(List<IamRole> roles, IamAuth iamAuth);

    /**
     * 是否是造物主
     *
     * @param userId
     * @return
     */
    boolean isCreator(Long userId);

    /**
     * 授予权限
     *
     * @param userId
     * @param dataId
     * @param iamRole
     * @return
     */
    boolean addRole(Long userId, Long dataId, IamRole iamRole);

    boolean rmRole(Long userId, Long dataId, IamRole iamRole);

    List<Long> listDataIdByAuth(Long userId, IamAuth iamAuth);

    List<Long> listUserIdByData(Long dataId, IamAuth iamAuth);
}
