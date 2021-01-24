/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2021 All Rights Reserved.
 */
package com.iqing.simple.rpc.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author aiqing.wyx
 * @version NIOServer.java, v 0.1 2021年01月22日 1:37 下午 wangyuxiang Exp $
 */
public class NIOServer {

    public static void main(String[] args) throws IOException, InterruptedException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(9999));

        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {

            if (selector.selectNow() == 0) {
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> selectionKeyIterator = selectionKeys.iterator();

            while (selectionKeyIterator.hasNext()) {

                SelectionKey selectionKey = selectionKeyIterator.next();

                if (selectionKey.isAcceptable()) {

                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));

                }

                if (selectionKey.isReadable()) {

                    SocketChannel socketChannel = (SocketChannel) selectionKey.channel();

                    System.out.println("selectionKey: " + selectionKey.hashCode());

                    ByteBuffer buffer = (ByteBuffer) selectionKey.attachment();

                    System.out.println("buffer: " + buffer.hashCode());

                    socketChannel.read(buffer);

                    System.out.println("收到数据: " + new String(buffer.array()));

                    // 这个地方不清除buffer的话会出bug，
                    // 这里会重复收到客户端上次的数据，客户端多发送几次请求 就能复现
                    // 具体原因还没搞清楚
                    buffer.clear();
                }


                selectionKeyIterator.remove();
            }
        }

    }

}