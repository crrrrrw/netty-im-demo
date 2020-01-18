package com.crw.netty.im.protocal;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public abstract class Packet {

    /**
     * 请求id
     */
    private String reqId;

    /**
     * 序列化方法
     */
    private Byte serializeMethod;

    /**
     * 协议版本
     */
    @JSONField(deserialize = false, serialize = false)
    private Byte version = 1;

    @JSONField(serialize = false)
    public abstract Byte getCommand();
}
