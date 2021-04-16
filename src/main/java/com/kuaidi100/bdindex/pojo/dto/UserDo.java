package com.kuaidi100.bdindex.pojo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * T_RBAC_USER
 *
 * @author
 */
@Data
public class UserDo implements Serializable {
    private Long id;

    private Long clientId;

    private String username;

    private Date createTime;

    private Date updateTime;

    private Integer status;

//    private List<RoleDo> roleDos;

}
