/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

/**
 * @author aiqing.wyx
 * @version ChatClient.java, v 0.1 2021年01月24日 8:20 下午 wangyuxiang Exp $
 */
public class ChatClient {

    private Selector selector;

    private SocketChannel socketChannel;

    private static final String IP = "127.0.0.1";

    private static final int PORT = 9999;

    public ChatClient() {
        try {
            selector = Selector.open();
            socketChannel = SocketChannel.open();
            Socket socket = socketChannel.socket();
            socket.connect(new InetSocketAddress(IP, PORT));
            socketChannel.configureBlocking(false);
            socketChannel.register(selector, SelectionKey.OP_READ);
        } catch (IOException e) {
            System.out.println("client 启动失败！！！");
        }
    }

    public void sendMsg(String msg) {

        ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
        try {
            socketChannel.write(byteBuffer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMsg() {
        try {
            int count = selector.select(2000);

            if (count > 0) {
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey selectionKey = iterator.next();

                    if (selectionKey.isReadable()) {

                        SocketChannel channel = (SocketChannel) selectionKey.channel();

                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        int read = channel.read(byteBuffer);

                        if (read > 0) {
                            System.out.println("收到数据");
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("未知异常");
        }
    }

    public static void main(String[] args) {
        ChatClient chatClient = new ChatClient();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    chatClient.receiveMsg();
                }
            }
        }).start();

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String msg = sc.nextLine();
            chatClient.sendMsg(msg);
        }
    }
}