package com.crw.netty.im.server;

import com.crw.netty.im.codec.FastJsonCodeC;
import com.crw.netty.im.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

@Slf4j
public class Server {

    private EventLoopGroup boss = new NioEventLoopGroup();
    private EventLoopGroup work = new NioEventLoopGroup();

    private static int port = 9090;

    public static void main(String[] args) {
        new Server().start();
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                //配置TCP参数，握手字符串长度设置
                .option(ChannelOption.SO_BACKLOG, 1024)
                //保持长连接
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                //10 秒没有向客户端发送消息就发生心跳
                                .addLast(new IdleStateHandler(10, 0, 0))
                                .addLast(new FastJsonCodeC())
                                .addLast(new ServerHandler());
                    }
                });

        try {
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                System.out.println("server started");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
