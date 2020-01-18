package com.crw.netty.im.codec;

import com.alibaba.fastjson.JSON;
import com.crw.netty.im.vo.RequestVO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Auther: dell
 * @Date: 2020/1/17 10:40
 * @Description:
 */
public class FastJsonEncoder extends MessageToByteEncoder<RequestVO> {

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, RequestVO msg, ByteBuf out) {
        byte[] body = JSON.toJSONString(msg).getBytes();
        int dataLength = body.length;
        msg.setDataLength(dataLength);
        out.writeInt(dataLength);
        out.writeBytes(body);
    }
}
