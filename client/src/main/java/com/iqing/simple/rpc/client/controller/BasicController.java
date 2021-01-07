/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.client.controller;

import com.iqing.simple.rpc.client.model.BasicResponse;
import com.iqing.simple.rpc.client.model.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aiqing.wyx
 * @version BasicController.java, v 0.1 2021年01月07日 11:10 下午 wangyuxiang Exp $
 */
@RestController
public class BasicController {

    @GetMapping("/basic")
    public BasicResponse<Student> getStudent() {
        return null;
    }
}