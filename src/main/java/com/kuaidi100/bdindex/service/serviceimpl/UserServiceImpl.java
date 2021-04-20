package com.kuaidi100.bdindex.service.serviceimpl;

import com.kuaidi100.bdindex.mapper.*;
import com.kuaidi100.bdindex.pojo.dto.*;
import com.kuaidi100.bdindex.pojo.req.UserAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.resp.UserInfoVo;
import com.kuaidi100.bdindex.service.IUserService;
import com.kuaidi100.exception.BusinessException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @author zefeng_lin
 * @date 2021-04-01 14:49
 * @description
 */
@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    UserRoleMapper userRoleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermMapper rolePermMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(UserAddOrUpdateReq userAddOrUpdateReq) {
        Long id = userAddOrUpdateReq.getId();
        List<Long> roleIds = userAddOrUpdateReq.getRoleIds();
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userAddOrUpdateReq, userDo);
        userDo.setUpdateTime(new Date());
        BeanUtils.copyProperties(userAddOrUpdateReq, userDo);
        if (Objects.nonNull(id) && id > 0) {
            userMapper.update(userDo);
        } else {
            userDo.setCreateTime(new Date());
            id = userMapper.add(userDo);
        }
        if (!CollectionUtils.isEmpty(roleIds)) {
            Long userId = userDo.getId();
            //删除原来的,新增新的
            userRoleMapper.deleteByUserId(id);
            ArrayList<UserRoleDo> userRoleDos = new ArrayList<UserRoleDo>();
            for (Long roleId : roleIds) {
                UserRoleDo userRoleDo = new UserRoleDo();
                userRoleDo.setRoleId(roleId);
                userRoleDo.setUserId(userId);
                userRoleDo.setCreateTime(new Date());
                userRoleDos.add(userRoleDo);
            }
            userRoleMapper.batchInsert(userRoleDos);
        }
    }

    @Override
    public void delete(Long userId) {
        userMapper.deleteById(userId);
    }

    @Override
    public UserDo findByUserId(Long userId) {
        UserDo userDo = userMapper.findById(userId);
        return userDo;
    }

    @Override
    public UserInfoVo getUserInfo(Long clientId) {
        UserInfoVo userInfoVo = new UserInfoVo();
        ArrayList<UserInfoVo.AuthInfo> authInfos = new ArrayList<>();
        ArrayList<UserInfoVo.RoleInfo> roleInfos = new ArrayList<>();
        userInfoVo.setAuthInfos(authInfos);
        userInfoVo.setRoleInfos(roleInfos);
        UserDo userDo = userMapper.findByClientId(clientId);
        if (Objects.isNull(userDo)) {
            throw new AuthenticationCredentialsNotFoundException("用户不存在");
        }
        Long userDoId = userDo.getId();
        List<UserRoleDo> userRoles = userRoleMapper.findAllByUserId(userDoId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return userInfoVo;
        }
        ArrayList<Long> roleIds = new ArrayList<>();
        for (UserRoleDo userRole : userRoles) {
            roleIds.add(userRole.getRoleId());
//            UserInfoResp.RoleInfo roleInfo = new UserInfoResp.RoleInfo();
//            roleInfo.setName();
//            roleInfos.add(roleInfo);
        }
        List<RoleDo> roleDos = roleMapper.findByIds(roleIds);
        for (RoleDo roleDo : roleDos) {
            UserInfoVo.RoleInfo roleInfo = new UserInfoVo.RoleInfo();
            roleInfo.setName(roleDo.getName());
            roleInfos.add(roleInfo);
        }
        List<RolePermDo> rolePermDos = rolePermMapper.findAllByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(rolePermDos)) {
            return userInfoVo;
        }
        ArrayList<Long> permissionIds = new ArrayList<Long>();
        for (RolePermDo rolePermDo : rolePermDos) {
            permissionIds.add(rolePermDo.getPermissionId());
        }
        List<PermissionDo> permissionDos = permissionMapper.findByIds(permissionIds);
        for (PermissionDo permissionDo : permissionDos) {
            UserInfoVo.AuthInfo authInfo = new UserInfoVo.AuthInfo();
            authInfo.setName(permissionDo.getName());
            authInfo.setType(permissionDo.getType());
        }
        return userInfoVo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createAdminUser() {
        Long clientId = 178812931L;
        UserDo user = userMapper.findByClientId(clientId);
        if (Objects.isNull(user)) {
            //新增一个用户
            UserDo userDo = new UserDo();
            userDo.setClientId(clientId);
            userDo.setStatus(1);
            userDo.setUpdateTime(new Date());
            userDo.setCreateTime(new Date());
            userDo.setUsername("");
            userMapper.add(userDo);
            Long userId = userDo.getId();
            //新增一个角色
            RoleDo admin = roleMapper.findByName("ADMIN");
            Long roleId = 0L;
            if (Objects.isNull(admin)) {
                RoleDo roleDo = new RoleDo();
                roleDo.setName("ADMIN");
                roleDo.setCreateTime(new Date());
                roleDo.setUpdateTime(new Date());
                roleDo.setDesc("超级管理员");
                roleDo.setStatus(1);
                roleMapper.add(roleDo);
                roleId = roleDo.getId();
            }
            //关联用户和角色
            UserRoleDo userRoleDo = new UserRoleDo();
            userRoleDo.setCreateTime(new Date());
            userRoleDo.setRoleId(roleId);
            userRoleDo.setUserId(userId);
            userRoleMapper.insertSelective(userRoleDo);
        }

    }

    @Override
    public UserDo findByClientId(Long clientId) {
        UserDo userDo = userMapper.findByClientId(clientId);
        return userDo;
    }
}
