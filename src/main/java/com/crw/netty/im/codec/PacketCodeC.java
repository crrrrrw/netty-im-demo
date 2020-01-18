package com.crw.netty.im.codec;

import com.crw.netty.im.protocal.Command;
import com.crw.netty.im.protocal.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

import java.util.HashMap;
import java.util.Map;

public class PacketCodeC {

    private static final int MAGIC_NUMBER = 0x12345678;
    public static final PacketCodeC INSTANCE = new PacketCodeC();

    private final Map<Byte, Serializer> serializerMap;


    private PacketCodeC() {
        serializerMap = new HashMap<>();
        Serializer serializer = new FastJsonSerializer();
        serializerMap.put(serializer.serializeMethod(), serializer);
    }


    public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
        // 1. 创建 ByteBuf 对象
        ByteBuf byteBuf = byteBufAllocator.ioBuffer();
        // 2. 序列化 java 对象
        Serializer serializer = serializerMap.getOrDefault(packet.getSerializeMethod(),
                FastJsonSerializer.getInstance());
        byte[] bytes = serializer.encode(packet);

        // 3. 实际编码过程
        byteBuf.writeInt(MAGIC_NUMBER);
        // TODO NULLPOINT
        byteBuf.writeByte(packet.getVersion());
        byteBuf.writeByte(serializer.serializeMethod());
        byteBuf.writeByte(packet.getCommand());
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);

        return byteBuf;
    }


    public Packet decode(ByteBuf byteBuf) {
        // 跳过 magic number
        byteBuf.skipBytes(4);

        // 跳过版本号
        byteBuf.skipBytes(1);

        // 序列化算法
        byte serializeMethod = byteBuf.readByte();

        // 指令
        byte command = byteBuf.readByte();

        // 数据包长度
        int length = byteBuf.readInt();

        byte[] bytes = new byte[length];
        byteBuf.readBytes(bytes);

        Class<? extends Packet> requestType = getRequestType(command);
        Serializer serializer = getSerializer(serializeMethod);

        if (requestType != null && serializer != null) {
            return serializer.decode(requestType, bytes);
        }

        return null;
    }

    private Serializer getSerializer(byte serializeMethod) {
        return serializerMap.get(serializeMethod);
    }

    private Class<? extends Packet> getRequestType(byte command) {
        return Command.getByType(command).packetType();
    }


}
