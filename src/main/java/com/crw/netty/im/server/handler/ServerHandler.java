package com.crw.netty.im.server.handler;

import com.crw.netty.im.protocol.SingleMsgPacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // TODO 转发消息
        System.out.printf("recive msg: %s, class: %s \n", msg, msg.getClass());
        ctx.channel().writeAndFlush(SingleMsgPacket.builder()
                .destUserId(1L)
                .fromUserId(2L)
                .reqId("123")
                .msg("fine thank you ,and u")
                .build());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();// 关闭通道
    }
}
