/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.configuration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangyuxiang
 * @version 配置中心启动类: ConfigurationServer.java, v 0.1 2021年01月04日 8:32 下午 wangyuxiang Exp $
 */

@SpringBootApplication
public class ConfigurationServer {
    public static void main(String[] args) {
        SpringApplication.run(ConfigurationServer.class, args);
    }
}