package com.kuaidi100.bdindex.pojo.dto;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * T_RBAC_ROLE_PERM
 * @author
 */
@Data
public class RolePermDo implements Serializable {
    private Long roleId;

    private Long permissionId;

    private Date createTime;

}
