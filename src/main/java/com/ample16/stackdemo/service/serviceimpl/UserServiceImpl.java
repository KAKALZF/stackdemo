package com.ample16.stackdemo.service.serviceimpl;

import com.ample16.stackdemo.mapper.*;
import com.ample16.stackdemo.pojo.dto.*;
import com.ample16.stackdemo.pojo.req.UserAddOrUpdateReq;
import com.ample16.stackdemo.pojo.resp.UserInfoResp;
import com.ample16.stackdemo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
    public UserInfoResp getUserInfo(Long clientId) {
        UserInfoResp userInfoResp = new UserInfoResp();
        ArrayList<UserInfoResp.AuthInfo> authInfos = new ArrayList<>();
        ArrayList<UserInfoResp.RoleInfo> roleInfos = new ArrayList<>();
        userInfoResp.setAuthInfos(authInfos);
        userInfoResp.setRoleInfos(roleInfos);
        UserDo userDo = userMapper.findByClientId(clientId);
        Long userDoId = userDo.getId();
        List<UserRoleDo> userRoles = userRoleMapper.findAllByUserId(userDoId);
        if (CollectionUtils.isEmpty(userRoles)) {
            return userInfoResp;
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
            UserInfoResp.RoleInfo roleInfo = new UserInfoResp.RoleInfo();
            roleInfo.setName(roleDo.getName());
            roleInfos.add(roleInfo);
        }
        List<RolePermDo> rolePermDos = rolePermMapper.findAllByRoleIds(roleIds);
        if (CollectionUtils.isEmpty(rolePermDos)) {
            return userInfoResp;
        }
        ArrayList<Long> permissionIds = new ArrayList<Long>();
        for (RolePermDo rolePermDo : rolePermDos) {
            permissionIds.add(rolePermDo.getPermissionId());
        }
        List<PermissionDo> permissionDos = permissionMapper.findByIds(permissionIds);
        for (PermissionDo permissionDo : permissionDos) {
            UserInfoResp.AuthInfo authInfo = new UserInfoResp.AuthInfo();
            authInfo.setName(permissionDo.getName());
            authInfo.setType(permissionDo.getType());
        }
        return userInfoResp;
    }
}
