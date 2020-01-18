package com.crw.netty.im.codec;

public enum SerializeMethod {
    JSON((byte) 1);

    private byte method;

    SerializeMethod(byte method) {
        this.method = method;
    }

    public byte method() {
        return method;
    }
}
