package com.kuaidi100.bdindex.service.serviceimpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kuaidi100.bdindex.controller.DataController;
import com.kuaidi100.bdindex.controller.PermissionController;
import com.kuaidi100.bdindex.controller.RoleController;
import com.kuaidi100.bdindex.controller.UserController;
import com.kuaidi100.bdindex.mapper.PermissionMapper;
import com.kuaidi100.bdindex.pojo.dto.PermissionDo;
import com.kuaidi100.bdindex.pojo.req.PermissionAddOrUpdateReq;
import com.kuaidi100.bdindex.pojo.req.PermissionsQueryReq;
import com.kuaidi100.bdindex.pojo.resp.AreaDataStrVo;
import com.kuaidi100.bdindex.pojo.resp.RouteDataStrVo;
import com.kuaidi100.bdindex.sercurity.config.AuthPermit;
import com.kuaidi100.bdindex.service.IPermissionService;
import com.kuaidi100.bdindex.util.CommonUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author zefeng_lin
 * @date 2021-04-01 15:28
 * @description
 */
@Service("permissionService")
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public void addOrUpdate(PermissionAddOrUpdateReq permissionAddOrUpdateReq) {
        PermissionDo permissionDo = new PermissionDo();
        BeanUtils.copyProperties(permissionAddOrUpdateReq, permissionDo);
        Long id = permissionDo.getId();
        if (Objects.nonNull(id) && id > 0) {
            permissionDo.setUpdateTime(new Date());
            permissionMapper.update(permissionDo);
        } else {
            permissionDo.setCreateTime(new Date());
            permissionDo.setUpdateTime(new Date());
            permissionMapper.insertSelective(permissionDo);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long permissionId) {
        permissionMapper.deleteById(permissionId);
    }

    @Override
    public PermissionDo findByPermissionId(Long permissionId) {
        PermissionDo permissionDo = permissionMapper.findById(permissionId);
        return permissionDo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void loadAllPermissions() {
        List<AuthPermit> authPermits = CommonUtil.getAllAuth();
        List<Long> allId = permissionMapper.findAllId();
        for (AuthPermit authPermit : authPermits) {
            String authName = authPermit.authName();
            String zhName = authPermit.zhName();
            int type = authPermit.type();
            String desc = authPermit.desc();
            zhName = StringUtils.isEmpty(zhName) ? authName : zhName;
            desc = StringUtils.isEmpty(desc) ? zhName : desc;
            PermissionDo perm = permissionMapper.findByName(authName);
            if (Objects.nonNull(perm)) {
                //更新
                perm.setStatus(1);
                perm.setUpdateTime(new Date());
                perm.setType(type);
                perm.setZhName(zhName);
                permissionMapper.update(perm);
                allId.remove(perm.getId());
            } else {
                //新增
                PermissionDo permissionDo = new PermissionDo();
                permissionDo.setName(authName);
                permissionDo.setType(type);
                permissionDo.setDesc(desc);
                permissionDo.setCreateTime(new Date());
                permissionDo.setUpdateTime(new Date());
                permissionDo.setStatus(1);
                permissionDo.setZhName(zhName);
                permissionMapper.insertSelective(permissionDo);
                allId.remove(permissionDo.getId());
            }
        }
        //软删除剩余的权限
        if (!CollectionUtils.isEmpty(allId)) {
            permissionMapper.deleteByIds(allId);
        }
    }

    @Override
    public PageInfo<PermissionDo> queryList(PermissionsQueryReq permissionsQueryReq) {
        Integer page = permissionsQueryReq.getPage();
        Integer size = permissionsQueryReq.getSize();
        PageHelper.startPage(page,size);
        List<PermissionDo> permissionDos = permissionMapper.queryList(permissionsQueryReq);
        PageInfo<PermissionDo> pageInfo = PageInfo.of(permissionDos);
        return pageInfo;
    }

}
