/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.iqing.simple.rpc.server.service.impl;

import com.iqing.simple.rpc.server.model.Student;
import com.iqing.simple.rpc.server.service.StudentService;
import org.springframework.stereotype.Service;

/**
 * @author wangyuxiang
 * @version 学生类服务: StudentService.java, v 0.1 2020年12月28日 10:51 下午 wangyuxiang Exp $
 */
@Service
public class StudentServiceImpl implements StudentService {

    public Student getStudentById(Long id) {

        Student student = new Student();
        student.setId(1L);
        student.setName("iqing");
        student.setAge(27);
        student.setSex(new Byte("1"));

        return student;
    }

    public void doSomething() {

    }
}