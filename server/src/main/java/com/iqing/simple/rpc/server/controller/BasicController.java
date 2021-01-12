/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.iqing.simple.rpc.server.controller;

import com.iqing.simple.rpc.server.model.BasicResponse;
import com.iqing.simple.rpc.server.model.Student;
import com.iqing.simple.rpc.server.service.impl.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wangyuxiang
 * @version 基础控制层: BasicController.java, v 0.1 2020年12月28日 10:41 下午 wangyuxiang Exp $
 */

@RestController
public class BasicController {

    @Autowired
    private StudentServiceImpl studentService;

    @GetMapping("/basic")
    public BasicResponse<Student> getStudentById(@RequestParam Long id) {
        return new BasicResponse().success(studentService.getStudentById(id));
    }
}