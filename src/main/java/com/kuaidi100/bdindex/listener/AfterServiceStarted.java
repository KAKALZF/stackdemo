package com.kuaidi100.bdindex.listener;

import com.kuaidi100.bdindex.pojo.dto.UserDo;
import com.kuaidi100.bdindex.service.IPermissionService;
import com.kuaidi100.bdindex.service.IUserService;
import com.kuaidi100.bdindex.service.serviceimpl.PermissionServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Component
@Slf4j
public class AfterServiceStarted implements ApplicationRunner {
    /**
     * 会在服务启动完成后立即执行
     */

    @Autowired
    IPermissionService permissionService;
    @Autowired
    IUserService userService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        userService.createAdminUser();
        permissionService.loadAllPermissions();
        log.info("Successful service startup!");
        //  billServiceImpl.billDirCreate();
    }
}
