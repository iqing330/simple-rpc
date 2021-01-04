/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.server.service;

import com.iqing.simple.rpc.server.model.Student;

/**
 * @author wangyuxiang
 * @version 学生类服务: StudentService.java, v 0.1 2021年01月04日 8:26 下午 wangyuxiang Exp $
 */
public interface StudentService {

    Student getStudentById(Long id);

    void doSomething();
}