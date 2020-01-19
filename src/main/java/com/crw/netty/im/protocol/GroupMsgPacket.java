package com.crw.netty.im.protocol;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class GroupMsgPacket extends Packet {

    private Long userId;
    private Long groupId;

    @Override
    public Byte getCommand() {
        return Command.GROUP_MSG.type();
    }
}

