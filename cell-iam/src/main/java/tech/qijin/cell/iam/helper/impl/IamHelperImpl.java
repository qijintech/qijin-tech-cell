package tech.qijin.cell.iam.helper.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;
import tech.qijin.cell.iam.db.dao.RoleAuthDao;
import tech.qijin.cell.iam.db.dao.UserRoleDao;
import tech.qijin.cell.iam.db.model.RoleAuth;
import tech.qijin.cell.iam.db.model.RoleAuthExample;
import tech.qijin.cell.iam.db.model.UserRole;
import tech.qijin.cell.iam.db.model.UserRoleExample;
import tech.qijin.cell.iam.helper.IamHelper;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IamHelperImpl implements IamHelper {
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleAuthDao roleAuthDao;

    @Override
    public List<IamRole> listRole(Long userId, Long dataId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andDataIdEqualTo(dataId);
        return userRoleDao.selectByExample(example)
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());
    }

    @Override
    public List<IamAuth> listAuth(Long userId, Long dataId) {
        return listAuthByRoles(listRole(userId, dataId)
                .stream()
                .collect(Collectors.toList()));
    }

    @Override
    public List<IamAuth> listAuthByRole(IamRole role) {
        RoleAuthExample example = new RoleAuthExample();
        example.createCriteria()
                .andRoleEqualTo(role);
        Set<IamAuth> authSet = roleAuthDao.selectByExample(example)
                .stream()
                .map(RoleAuth::getAuth)
                .collect(Collectors.toSet());
        return Lists.newArrayList(authSet);
    }

    @Override
    public List<IamAuth> listAuthByRoles(List<IamRole> roles) {
        if (roles.contains(IamRole.CREATOR)) {
            return Lists.newArrayList(IamAuth.values());
        }
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }
        RoleAuthExample example = new RoleAuthExample();
        example.createCriteria()
                .andRoleIn(roles);
        Set<IamAuth> authSet = roleAuthDao.selectByExample(example)
                .stream()
                .map(RoleAuth::getAuth)
                .collect(Collectors.toSet());
        return Lists.newArrayList(authSet);
    }
}
