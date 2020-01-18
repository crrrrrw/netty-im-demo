package com.crw.netty.im.codec;

import lombok.Data;

/**
 * 序列化器
 */
public interface Serializer {

    // Serializer DEFAULT = new JSONSerializer();

    /**
     * 序列化算法
     */
    byte serializeMethod();

    /**
     * java 对象转换成二进制
     */
    byte[] encode(Object object);

    /**
     * 二进制转换成 java 对象
     */
    <T> T decode(Class<T> clazz, byte[] bytes);

}
