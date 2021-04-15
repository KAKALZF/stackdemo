package com.ample16.stackdemo.pojo.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-01 19:08
 * @description
 */
@Data
public class UserRoleDo {
    private Long userId;

    private Long roleId;

    private Date createTime;


}
