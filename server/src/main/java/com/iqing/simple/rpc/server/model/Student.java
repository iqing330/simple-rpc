/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.iqing.simple.rpc.server.model;

import java.io.Serializable;
import java.net.InetAddress;

/**
 * @author wangyuxiang
 * @version : Student.java, v 0.1 2020年12月28日 10:45 下午 wangyuxiang Exp $
 */
public class Student implements Serializable {

    private static final long serialVersionUID = 2100651228620252429L;

    private Long id;

    private String name;

    private Integer age;

    private Byte sex;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public static void main(String[] args) {
        InetAddress addr=null;
        String ip="";
        String address="";
        try{
            // cp01-fengchao-public-7.epc.baidu.com|10.95.130.73
            //  addr = InetAddress.getByName("cp01-fengchao-public-7.epc.baidu.com");
            addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress().toString(); //获得机器IP　　
            address = addr.getHostName().toString(); //获得机器名称
            System.out.println(ip + "|" + address);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}