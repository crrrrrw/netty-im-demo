package com.crw.netty.im.codec;

import com.crw.netty.im.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    private static final int MAGIC_NUMBER = 0x666666;

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
        // 序列化 java 对象
        Serializer serializer = SerializerHolder.get(msg.getSerializeMethod());
        byte[] bytes = serializer.encode(msg);

        // 编码
        out.writeInt(MAGIC_NUMBER);
        out.writeByte(msg.getVersion());
        out.writeByte(serializer.serializeMethod());
        out.writeByte(msg.getCommand());
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
