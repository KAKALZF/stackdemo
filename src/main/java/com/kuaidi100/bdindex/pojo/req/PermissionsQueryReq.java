package com.kuaidi100.bdindex.pojo.req;

import com.kuaidi100.bdindex.pojo.BaseQuery;
import lombok.Data;

/**
 * @author zefeng_lin
 * @date 2021-04-20 11:33
 * @description
 */
@Data
public class PermissionsQueryReq extends BaseQuery {
    String name;
    String zhName;
}
