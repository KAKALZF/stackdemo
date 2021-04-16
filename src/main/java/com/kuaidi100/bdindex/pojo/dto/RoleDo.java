package com.kuaidi100.bdindex.pojo.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * T_RBAC_ROLE
 *
 * @author
 */
@Data
public class RoleDo implements Serializable {
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Integer status;

//    private List<PermissionDo> permissionDoList;

}
