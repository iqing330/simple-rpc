/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.client.utils;

import com.alibaba.fastjson.JSON;
import com.iqing.simple.rpc.client.model.BasicResponse;
import com.iqing.simple.rpc.client.model.Student;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


/**
 * @author wangyuxiang
 * @version http服务类: HttpUtil.java, v 0.1 2021年01月05日 7:20 下午 wangyuxiang Exp $
 */

public class HttpUtil {


    public static synchronized BasicResponse<Student> callRemoteService() {
        try {
            String result = sendPost("http://127.0.0.1:8080/");
            return JSON.parseObject(result, BasicResponse.class);
        } catch (Exception ex) {
            return null;
        }
    }


    public static synchronized String sendPost(String url) throws Exception{

        CloseableHttpResponse response = null;

        try {
            HttpPost httpPost = new HttpPost(url);

            CloseableHttpClient client = HttpClients.createDefault();

            response = client.execute(httpPost);

            int statusCode = response.getStatusLine().getStatusCode();
            if (200 == statusCode) {
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(response != null) {
                response.close();
            }
        }
        return null;
    }
}