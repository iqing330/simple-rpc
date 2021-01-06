/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.client.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author wangyuxiang
 * @version 定时任务: Schedule.java, v 0.1 2021年01月05日 7:14 下午 wangyuxiang Exp $
 */

@Component
public class Schedule {



    @Scheduled(cron = " 0/10 * * * * ? ")
    public void scheduleTask() {

    }
}