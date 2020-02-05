package com.crw.netty.im.demo.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GroupChatServerHandler extends ChannelInboundHandlerAdapter {

    // 定义一个 channel 组， 管理所有 channel
    // GlobalEventExecutor.INSTANCE 单例模式，全局事件执行器
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    // 连接一建立，第一个被执行，因此在这将当前 channle 加入到 channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        // 给 channelGroup 中每个 channel 发送消息
        channelGroup.writeAndFlush("[client] " + channel.remoteAddress() + " 加入聊天\n");
        // 将客户加入聊天的信息推送给其他在线客户端
        channelGroup.add(channel);
    }

    // 连接断开触发，给群里每个人推送下线消息
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("[client] " + channel.remoteAddress() + " 退出聊天\n");
        System.out.println("目前 channel size : " + channelGroup.size());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 上线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress() + " 下线");
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Channel channel = ctx.channel();
        // 遍历 channelGroup，根据不同情况发送不同消息
        channelGroup.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush("[client] " + channel.remoteAddress() + " 发送了消息："
                        + msg + "\n");
            } else {
                ch.writeAndFlush("[me]发送了消息" + msg + "\n");
            }
        });

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
