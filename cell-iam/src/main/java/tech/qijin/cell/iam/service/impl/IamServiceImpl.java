package tech.qijin.cell.iam.service.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;
import tech.qijin.cell.iam.db.model.UserRole;
import tech.qijin.cell.iam.helper.IamHelper;
import tech.qijin.cell.iam.service.IamService;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IamServiceImpl implements IamService {
    @Autowired
    private IamHelper iamHelper;

    @Override
    public List<IamRole> listRole(Long userId, Long dataId) {
        return iamHelper.listRole(userId, dataId);
    }

    @Override
    public boolean hasAuth(List<IamRole> roles, IamAuth iamAuth) {
        if (roles.contains(IamRole.CREATOR)) {
            return true;
        }
        return iamHelper.listAuthByRoles(roles).contains(iamAuth);
    }

    @Override
    public boolean hasAuth(Long userId, Long dataId, IamAuth iamAuth) {
        return hasAuth(iamHelper.listRole(userId, dataId), iamAuth);
    }

    @Override
    public boolean hasAuth(Long userId, IamAuth iamAuth) {
        Set<IamRole> roles = iamHelper.listRole(userId);
        return iamHelper.listAuthByRoles(Lists.newArrayList(roles))
                .stream()
                .collect(Collectors.toSet())
                .contains(iamAuth);
    }

    @Override
    public boolean isCreator(Long userId) {
        return listRole(userId, 0L).contains(IamRole.CREATOR);
    }

    @Override
    public boolean addRole(Long userId, Long dataId, IamRole iamRole) {
        return iamHelper.addRole(userId, dataId, iamRole);
    }

    @Override
    public boolean rmRole(Long userId, Long dataId, IamRole iamRole) {
        return iamHelper.rmRole(userId, dataId, iamRole);
    }

    @Override
    public List<Long> listDataIdByAuth(Long userId, IamAuth iamAuth) {
        List<UserRole> userRoles = iamHelper.listUserRole(userId);
        if (CollectionUtils.isEmpty(userRoles)) return Collections.emptyList();

        return userRoles.stream()
                .filter(userRole -> iamHelper.listAuthByRole(userRole.getRole()).contains(iamAuth))
                .map(UserRole::getDataId)
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> listUserIdByData(Long dataId, IamAuth iamAuth) {
        List<IamRole> roles = iamHelper.listRoleByAuth(iamAuth);
        if (CollectionUtils.isEmpty(roles)) return Lists.newArrayList();
        List<UserRole> userRoles = iamHelper.listUserIdByRoleAndData(dataId, roles);
        return Lists.newArrayList(userRoles.stream()
                .map(UserRole::getUserId)
                .collect(Collectors.toSet()));
    }
}
