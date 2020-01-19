package com.crw.netty.im.client.handler;

import com.crw.netty.im.codec.SerializeMethod;
import com.crw.netty.im.model.User;
import com.crw.netty.im.protocol.SingleMsgPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("client recive:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        System.out.println("connect success!");

        // 写数据
//        ctx.writeAndFlush(SingleMsgPacket.builder()
//                .fromUserId(1L)
//                .destUserId(2L)
//                .msg("hello~")
//                .reqId(UUID.randomUUID().toString())
//                .serializeMethod(SerializeMethod.JSON.method())
//                .version((byte) 1)
//                .build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();// 关闭通道
    }
}
