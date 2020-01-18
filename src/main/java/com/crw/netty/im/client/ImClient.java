package com.crw.netty.im.client;

import com.crw.netty.im.client.handler.EchoClientHandler;
import com.crw.netty.im.codec.FastJsonDecoder;
import com.crw.netty.im.codec.FastJsonEncoder;
import com.crw.netty.im.exception.ImException;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * 客户端类
 */
public class ImClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    private final String serverHost = "localhost";
    private final int serverPort = 90909;
    private String userName;// 账号
    private String password;// 密码

    public ImClient(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    private void init() {
        // 检查账号密码
        boolean checkAccount = checkAccount(userName, password);
        if (!checkAccount) {
            throw new ImException("用户名或者密码不正确");
        }
    }

    public void start() throws Exception {
        try {
            init();
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(serverHost, serverPort))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) {
                            ch.pipeline()
                                    .addLast(new FastJsonDecoder())
                                    .addLast(new FastJsonEncoder())
                                    .addLast(new EchoClientHandler());
                        }
                    });

            ChannelFuture f = b.connect().sync();

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    private boolean checkAccount(String userName, String password) {
        // TODO impl
        return true;
    }

    public void send(String userId, String text) {

    }
}
