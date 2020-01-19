package com.crw.netty.im.codec;

import com.crw.netty.im.protocol.Command;
import com.crw.netty.im.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder{

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // skip magic number
        in.skipBytes(4);
        // version
        in.skipBytes(1);
        // serialize method
        byte serializeMethod = in.readByte();
        // command
        byte command = in.readByte();
        // data length
        int length = in.readInt();
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        // packet type
        Class<? extends Packet> packetType = Command.getByType(command).packetType();
        // serializer
        Serializer serializer = SerializerHolder.get(serializeMethod);
        // data
        Packet packet = serializer.decode(packetType, bytes);

        out.add(packet);

    }
}
