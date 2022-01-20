package tech.qijin.cell.iam.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.qijin.cell.iam.base.IamAuth;
import tech.qijin.cell.iam.base.IamRole;
import tech.qijin.cell.iam.helper.IamHelper;
import tech.qijin.cell.iam.service.IamService;

import java.util.List;

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
        return iamHelper.listAuth(userId, dataId).contains(iamAuth);
    }

    @Override
    public boolean isCreator(Long userId) {
        return listRole(userId, 0L).contains(IamRole.CREATOR);
    }

    @Override
    public boolean addRole(Long userId, Long dataId, IamRole iamRole) {
        return iamHelper.addRole(userId, dataId, iamRole);
    }
}
