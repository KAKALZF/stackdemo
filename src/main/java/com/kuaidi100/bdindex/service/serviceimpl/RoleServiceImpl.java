package com.kuaidi100.bdindex.service.serviceimpl;

import com.kuaidi100.bdindex.mapper.PermissionMapper;
import com.kuaidi100.bdindex.mapper.RoleMapper;
import com.kuaidi100.bdindex.mapper.RolePermMapper;
import com.kuaidi100.bdindex.pojo.dto.RoleDo;
import com.kuaidi100.bdindex.pojo.dto.RolePermDo;
import com.kuaidi100.bdindex.pojo.req.RoleAddOrUpdateReq;
import com.kuaidi100.bdindex.service.IRoleService;
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
 * @date 2021-04-01 15:26
 * @description
 */
@Service("roleService")
public class RoleServiceImpl implements IRoleService {

    @Autowired
    RoleMapper roleMapper;

    @Autowired
    PermissionMapper permissionMapper;

    @Autowired
    RolePermMapper rolePermMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrUpdate(RoleAddOrUpdateReq roleAddOrUpdateReq) {
        RoleDo roleDo = new RoleDo();
        List<Long> permissionIds = roleAddOrUpdateReq.getPermissionIds();
        BeanUtils.copyProperties(roleAddOrUpdateReq, roleDo);
        Long id = roleDo.getId();
        if (Objects.nonNull(id) && id > 0) {
            roleDo.setUpdateTime(new Date());
            roleMapper.update(roleDo);
        } else {
            roleDo.setCreateTime(new Date());
            roleDo.setUpdateTime(new Date());
            roleMapper.add(roleDo);
        }
        if (!CollectionUtils.isEmpty(permissionIds)) {
            //删除角色权限关联
            Long newId = roleDo.getId();
            rolePermMapper.deleteByRoleId(newId);
            ArrayList<RolePermDo> rolePermDos = new ArrayList<>();
            for (Long permissionId : permissionIds) {
                RolePermDo rolePermDo = new RolePermDo();
                rolePermDo.setCreateTime(new Date());
                rolePermDo.setRoleId(newId);
                rolePermDo.setPermissionId(permissionId);
                rolePermDos.add(rolePermDo);
            }
            rolePermMapper.batchInsert(rolePermDos);
        }

    }


    @Override
    public void delete(Long roleId) {

    }

    @Override
    public void findByRoleId(Long roleId) {

    }
}
