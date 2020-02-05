package com.crw.netty.im.demo.groupchat;

import com.crw.netty.im.codec.PacketCodec;
import com.crw.netty.im.server.Server;
import com.crw.netty.im.server.handler.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;

public class GroupChatServer {

    private EventLoopGroup boss = new NioEventLoopGroup(1);
    private EventLoopGroup work = new NioEventLoopGroup();

    private static int port = 9090;

    public static void main(String[] args) {
        new GroupChatServer().start();
    }

    public void start() {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(boss, work)
                .channel(NioServerSocketChannel.class)
                .localAddress(new InetSocketAddress(port))
                .option(ChannelOption.SO_BACKLOG, 1024)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline()
                                .addLast("decoder", new StringDecoder())
                                .addLast("encoder", new StringEncoder())
                                .addLast(new GroupChatServerHandler());
                    }
                });

        try {
            ChannelFuture future = bootstrap.bind().sync();
            if (future.isSuccess()) {
                System.out.println("groupchat server started");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
