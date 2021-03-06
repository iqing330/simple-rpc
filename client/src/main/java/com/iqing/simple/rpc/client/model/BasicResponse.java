/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2020 All Rights Reserved.
 */
package com.iqing.simple.rpc.client.model;

import java.io.Serializable;

/**
 * @author wangyuxiang
 * @version 基础数据模型: BasicResponse.java, v 0.1 2020年12月28日 10:42 下午 wangyuxiang Exp $
 */
public class BasicResponse<T> implements Serializable {

    private static final long serialVersionUID = -8200481796842914432L;

    private Boolean success;

    private String dataType;

    private String message;

    private T jsonData;

    public BasicResponse success(T data) {
        BasicResponse basicResponse = new BasicResponse();
        basicResponse.setJsonData(data);
        basicResponse.setSuccess(Boolean.TRUE);
        return basicResponse;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getJsonData() {
        return jsonData;
    }

    public void setJsonData(T jsonData) {
        this.jsonData = jsonData;
    }
}