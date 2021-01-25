/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.netty.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * @author aiqing.wyx
 * @version ChatServer.java, v 0.1 2021年01月24日 8:20 下午 wangyuxiang Exp $
 */
public class ChatServer {

    private ServerSocketChannel listenChannel;

    private Selector selector;

    private static final int PORT = 9999;

    public ChatServer() {
        try {
            this.listenChannel = ServerSocketChannel.open();

            this.selector = Selector.open();

            listenChannel.socket().bind(new InetSocketAddress(PORT));

            listenChannel.configureBlocking(false);

            listenChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            System.out.println("ChatServer 启动失败！！！");
        }
    }

    public void listen() {

        while (true) {
            try {
                int count = selector.select(2000);

                if (count > 0) {
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();

                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    while (iterator.hasNext()) {
                        SelectionKey selectionKey = iterator.next();

                        // 建立连接
                        if (selectionKey.isAcceptable()) {

                            SocketChannel socketChannel = listenChannel.accept();

                            socketChannel.configureBlocking(false);

                            socketChannel.register(selector, SelectionKey.OP_READ);

                            System.out.println(socketChannel.getRemoteAddress() + "客户端连接了");
                        }
                        // 读数据
                        if (selectionKey.isReadable()) {

                            SocketChannel channel = null;
                            try {
                                channel = (SocketChannel) selectionKey.channel();

                                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                                int read = channel.read(byteBuffer);

                                if(read > 0) {
                                    String msg = new String(byteBuffer.array());

                                    //往其他客户端转发消息
                                    SocketChannel finalChannel = channel;
                                    selectionKeys.stream()
                                            .map(SelectionKey::channel)
                                            .filter(selectableChannel -> finalChannel != selectableChannel)
                                            .map(selectableChannel -> (SocketChannel)selectableChannel)
                                            .filter(Objects::nonNull)
                                            .forEach(
                                                    socketChannel -> {
                                                        ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                                                        try {
                                                            socketChannel.write(buffer);
                                                        } catch (IOException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                            );
                                }
                            }catch (IOException e) {
                                System.out.println(channel.getRemoteAddress() + "下线了");

                            }

                        }

                    }

                } else {
                    System.out.println("等待客户端连接。。。");
                }
            } catch (IOException e) {

            }

        }
    }

    public static void main(String[] args) {
        ChatServer chatServer = new ChatServer();

        chatServer.listen();
    }
}