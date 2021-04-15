package com.ample16.stackdemo.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * T_RBAC_PERMISSION
 * @author
 */
@Data
public class PermissionDo implements Serializable {
    private Long id;

    private String name;

    private Date createTime;

    private Date updateTime;

    private Integer status;
    private Integer type;

}
