package com.ample16.stackdemo.service.serviceimpl;

import com.ample16.stackdemo.mapper.PermissionMapper;
import com.ample16.stackdemo.pojo.dto.PermissionDo;
import com.ample16.stackdemo.pojo.req.PermissionAddOrUpdateReq;
import com.ample16.stackdemo.service.IPermissionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;
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
    public void delete(Long permissionId) {

    }

    @Override
    public void findByPermissionId(Long permissionId) {

    }
}
