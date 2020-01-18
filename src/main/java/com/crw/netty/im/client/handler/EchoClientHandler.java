package com.crw.netty.im.client.handler;

import com.crw.netty.im.codec.PacketCodeC;
import com.crw.netty.im.codec.SerializeMethod;
import com.crw.netty.im.model.User;
import com.crw.netty.im.protocal.SingleMsgPacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.UUID;

@ChannelHandler.Sharable
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("EchoClientHandler client recive:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 测试序列化，直接发送消息
        ctx.writeAndFlush(SingleMsgPacket.builder().msg(User.builder().id(1L).password("123").build()).build());
        // 编码
        ByteBuf buffer = PacketCodeC.INSTANCE.encode(ctx.alloc(),
                SingleMsgPacket.builder()
                        .fromUserId(1L)
                        .destUserId(2L)
                        .msg("hello~")
                        .reqId(UUID.randomUUID().toString())
                        .serializeMethod(SerializeMethod.JSON.method())
                        .build());

        // 写数据
        ctx.channel().writeAndFlush(buffer);
    }
}
