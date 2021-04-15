package com.ample16.stackdemo.service.serviceimpl;

import com.ample16.stackdemo.mapper.RoleMapper;
import com.ample16.stackdemo.mapper.UserMapper;
import com.ample16.stackdemo.mapper.UserRoleMapper;
import com.ample16.stackdemo.pojo.dto.UserDo;
import com.ample16.stackdemo.pojo.dto.UserRoleDo;
import com.ample16.stackdemo.pojo.req.UserAddOrUpdateReq;
import com.ample16.stackdemo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
}
