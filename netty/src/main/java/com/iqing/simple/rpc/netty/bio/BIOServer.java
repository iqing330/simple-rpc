/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.netty.bio;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author aiqing.wyx
 * @version BIOServer.java, v 0.1 2021年01月12日 10:47 下午 wangyuxiang Exp $
 */
public class BIOServer {

    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();

        final ServerSocket serverSocket = new ServerSocket(9001);

        while (true) {

            // 阻塞等待 客户端连接
            final Socket socket = serverSocket.accept();
            System.out.println("有客户端连接了！！！");

            executorService.submit(new Runnable() {
                public void run() {
                    doRun(socket);
                }
            });

        }
    }

    public static void doRun(Socket socket) {
        try {
            byte[] bytes = new byte[1024];

            InputStream in = socket.getInputStream();

            while (true) {

                int read = in.read(bytes);

                if (read != -1) {
                    System.out.println(new String(bytes, 0 ,read));
                } else {
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            try {
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}