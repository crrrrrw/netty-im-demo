package com.crw.netty.im.codec;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SerializerHolder {

    private final static Map<Byte, Serializer> SERIALIZER_MAP = new ConcurrentHashMap<>();

    static {
        FastJsonSerializer fastJsonSerializer = new FastJsonSerializer();
        SERIALIZER_MAP.put(fastJsonSerializer.serializeMethod(), fastJsonSerializer);
    }

    public static Serializer get(Byte serializeMethod) {
        return SERIALIZER_MAP.getOrDefault(serializeMethod, FastJsonSerializer.getInstance());
    }
}
