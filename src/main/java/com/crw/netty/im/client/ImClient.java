package com.crw.netty.im.client;

import com.crw.netty.im.client.handler.EchoClientHandler;
import com.crw.netty.im.codec.PacketCodec;
import com.crw.netty.im.exception.ImException;
import com.crw.netty.im.protocol.SingleMsgPacket;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * 客户端类
 */
public class ImClient {

    private EventLoopGroup group = new NioEventLoopGroup();

    private final String serverHost = "localhost";
    private final int serverPort = 9090;
    private String userName;// 账号
    private String password;// 密码
    private Channel channel;

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
        init();
        Bootstrap b = new Bootstrap();
        b.group(group)
                .channel(NioSocketChannel.class)
                .remoteAddress(new InetSocketAddress(serverHost, serverPort))
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ch.pipeline()
                                .addLast(new PacketCodec())
                                .addLast(new EchoClientHandler());
                    }
                });

        ChannelFuture future = b.connect().sync();

        if (future.isSuccess()) {
            System.out.println("client start success");
            channel = future.channel();
        }

    }

    private boolean checkAccount(String userName, String password) {
        // TODO impl
        return true;
    }

    public void send(String userId, String text) {
        SingleMsgPacket msg = SingleMsgPacket.builder()
                .destUserId(Long.valueOf(userId))
                .msg(text)
                .build();
        ChannelFuture future = channel.writeAndFlush(msg);
        future.addListener(channelFuture -> System.out.println("客户端发送消息:" + msg));
    }
}
