package com.crw.netty.im.codec;

import com.alibaba.fastjson.JSON;
import lombok.NoArgsConstructor;

/**
 * 基于fastjson的编解码
 */
@NoArgsConstructor
public class FastJsonSerializer implements Serializer {

    private static class FastJsonSerializerHolder {
        private static final Serializer INSTANCE = new FastJsonSerializer();
    }

    public static final Serializer getInstance() {
        return FastJsonSerializerHolder.INSTANCE;
    }

    @Override
    public byte serializeMethod() {
        return SerializeMethod.JSON.method();
    }

    @Override
    public byte[] encode(Object object) {
        return JSON.toJSONBytes(object);
    }

    @Override
    public <T> T decode(Class<T> clazz, byte[] bytes) {
        return JSON.parseObject(bytes, clazz);
    }
}
