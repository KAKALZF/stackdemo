package com.ample16.stackdemo.job;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.LockAssert;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zefeng_lin
 * @date 2020/11/06 16:42
 */
@Component
@Slf4j
public class Schedule {

    Logger logger = LoggerFactory.getLogger(Schedule.class);


    /**
     * 每月1号6点清除上月记录
     */
    @Scheduled(cron = "0/10 * 16 * * ?")
//    @SchedulerLock(name = "basicJob", lockAtMostFor = "1m", lockAtLeastFor = "1m")
    @SchedulerLock(name = "basicJob1")
    public void deleteLastMonthCache() {
        try {
            LockAssert.assertLocked();
            logger.info("alert=====job===");
        } catch (Exception e) {
            logger.error("告警定时任务执行异常", e);
        }
    }


}
