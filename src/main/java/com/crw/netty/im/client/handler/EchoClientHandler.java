package com.crw.netty.im.client.handler;

import com.crw.netty.im.model.User;
import com.crw.netty.im.vo.RequestVO;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @Auther: dell
 * @Date: 2020/1/17 13:57
 * @Description:
 */
@ChannelHandler.Sharable
public class EchoClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("EchoClientHandler client recive:" + msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        // 测试序列化，直接发送消息
        ctx.writeAndFlush(RequestVO.builder().data(User.builder().id(1L).password("123").build()).build());
    }
}
