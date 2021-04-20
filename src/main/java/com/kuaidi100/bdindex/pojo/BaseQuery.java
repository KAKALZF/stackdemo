package com.kuaidi100.bdindex.pojo;

import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-20 11:32
 * @description
 */
@Data
public abstract class BaseQuery {
    Integer page = 1;
    Integer size = 10;
}
