package com.ample16.stackdemo.pojo.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

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


}
