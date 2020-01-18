package com.crw.netty.im.client;

import com.crw.netty.im.client.handler.EchoClientHandler;
import com.crw.netty.im.codec.FastJsonDecoder;
import com.crw.netty.im.codec.FastJsonEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 客户端启动类
 */
public class ImClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("非法的参数");
            return;
        }

        final String host = args[0];
        final int port = Integer.parseInt(args[1]);

        new ImClient(host, port).start();
    }

    private final String host;
    private final int port;

    public ImClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
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

}
