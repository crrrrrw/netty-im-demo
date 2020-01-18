package com.crw.netty.im.protocal;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class GroupMsgPacket extends Packet {
    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG.type();
    }
}
