package com.ample16.stackdemo.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
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
