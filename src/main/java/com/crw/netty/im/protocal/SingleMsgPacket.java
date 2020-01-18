package com.crw.netty.im.protocal;

import lombok.Data;
import lombok.experimental.SuperBuilder;

/**
 * 单聊包
 */
@Data
@SuperBuilder
public class SingleMsgPacket extends Packet {

    private static final long serialVersionUID = -1302678252721280433L;
    private Long fromUserId;
    private Long destUserId;
    private Object msg;

    @Override
    public Byte getCommand() {
        return Command.SINGLE_MSG.type();
    }
}
