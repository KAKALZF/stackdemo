package com.ample16.stackdemo.service.serviceimpl;

import com.ample16.stackdemo.mapper.RoleMapper;
import com.ample16.stackdemo.mapper.UserMapper;
import com.ample16.stackdemo.mapper.UserRoleMapper;
import com.ample16.stackdemo.pojo.dto.UserDo;
import com.ample16.stackdemo.pojo.req.UserAddOrUpdateReq;
import com.ample16.stackdemo.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

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
    public void addOrUpdate(UserAddOrUpdateReq userAddOrUpdateReq) {
        Long id = userAddOrUpdateReq.getId();
        List<Long> roleIds = userAddOrUpdateReq.getRoleIds();
        UserDo userDo = new UserDo();
        BeanUtils.copyProperties(userAddOrUpdateReq, userDo);
        if (Objects.nonNull(id) && id > 0) {
            userMapper.update(userDo);
        } else {
            id = userMapper.add(userDo);
        }
        if (!CollectionUtils.isEmpty(roleIds)) {
            //删除原来的,新增新的
            userRoleMapper.deleteByUserId(id);

        }
    }

    @Override
    public void delete(Long userId) {

    }

    @Override
    public void findByUserId(Long userId) {

    }
}
