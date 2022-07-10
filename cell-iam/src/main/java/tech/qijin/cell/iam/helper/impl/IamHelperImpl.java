package tech.qijin.cell.iam.helper.impl;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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
import tech.qijin.util4j.trace.pojo.Channel;
import tech.qijin.util4j.trace.util.ChannelUtil;
import tech.qijin.util4j.web.pojo.LocalCacheAgent;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Service
public class IamHelperImpl implements IamHelper {
    @Autowired
    private UserRoleDao userRoleDao;
    @Autowired
    private RoleAuthDao roleAuthDao;

    private LocalCacheAgent<RoleAuth> roleAuthCache = new LocalCacheAgent<RoleAuth>();

    @PostConstruct
    @Scheduled(fixedDelay = 5000)
    public void reload() {
        ChannelUtil.setChannel(Channel.TEST);
        roleAuthCache.process(
                () -> roleAuthDao.getLastUpdatedAt(),
                () -> listRoleAuth());
    }

    @Override
    public List<UserRole> listUserRole(Long userId) {
        UserRoleExample example = new UserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId);
        return userRoleDao.selectByExample(example);
    }

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
        Set<IamAuth> authSet = roleAuthCache.get().stream()
                .filter(r -> r.getRole().equals(role))
                .map(RoleAuth::getAuth)
                .collect(Collectors.toSet());
        return Lists.newArrayList(authSet);
    }

    @Override
    public List<IamRole> listRoleByAuth(IamAuth auth) {
        return roleAuthCache.get().stream()
                .filter(r -> r.getAuth().equals(auth))
                .map(RoleAuth::getRole)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserRole> listUserIdByRoleAndData(Long dataId, List<IamRole> roles) {
        if (CollectionUtils.isEmpty(roles)) return Collections.emptyList();
        UserRoleExample example = new UserRoleExample();
        example.createCriteria()
                .andDataIdEqualTo(dataId)
                .andRoleIn(roles)
                .andValidEqualTo(true);
        return userRoleDao.selectByExample(example);
    }

    @Override
    public List<IamAuth> listAuthByRoles(List<IamRole> roles) {
        if (roles.contains(IamRole.CREATOR)) {
            return Lists.newArrayList(IamAuth.values());
        }
        if (CollectionUtils.isEmpty(roles)) {
            return Lists.newArrayList();
        }
        Set<IamAuth> authSet = roleAuthCache.get().stream()
                .filter(r -> roles.contains(r.getRole()))
                .map(RoleAuth::getAuth)
                .collect(Collectors.toSet());
        return Lists.newArrayList(authSet);
    }

    @Override
    public boolean addRole(Long userId, Long dataId, IamRole iamRole) {
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        userRole.setDataId(dataId);
        userRole.setRole(iamRole);
        userRole.setValid(true);
        return userRoleDao.insertSelective(userRole) > 0;
    }

    @Override
    public boolean rmRole(Long userId, Long dataId, IamRole iamRole) {
        UserRole userRole = new UserRole();
        userRole.setValid(false);

        UserRoleExample example = new UserRoleExample();
        example.createCriteria()
                .andUserIdEqualTo(userId)
                .andDataIdEqualTo(dataId)
                .andRoleEqualTo(iamRole);
        return userRoleDao.updateByExampleSelective(userRole, example) > 0;
    }

    private List<RoleAuth> listRoleAuth() {
        RoleAuthExample example = new RoleAuthExample();
        return roleAuthDao.selectByExample(example);
    }
}
