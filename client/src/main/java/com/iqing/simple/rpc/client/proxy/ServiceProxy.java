/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.client.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author wangyuxiang
 * @version 代理类: ServiceProxy.java, v 0.1 2021年01月05日 7:17 下午 wangyuxiang Exp $
 */
public class ServiceProxy<T> implements InvocationHandler {

    private T target;

    public ServiceProxy(T target) {
        this.target = target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return null;
    }
}