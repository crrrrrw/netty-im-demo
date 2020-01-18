package com.crw.netty.im.protocal;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 */
public enum Command {
    LOGIN((byte) 1, "login", null),
    LOGOUT((byte) 2, "logout", null),
    SINGLE_MSG((byte) 3, "singleMsg", SingleMsgPacket.class),
    GROUP_MSG((byte) 4, "groupMsg", GroupMsgPacket.class);

    Command(Byte type, String desc, Class packetType) {
        this.type = type;
        this.desc = desc;
        this.packetType = packetType;
    }

    private static Map<Byte, Command> typeToCmdMap = Stream.of(values())
            .collect(Collectors.toMap(Command::type, cmd -> cmd));

    private Byte type;
    private String desc;
    private Class packetType;

    public Byte type() {
        return type;
    }

    public String desc() {
        return desc;
    }

    public Class packetType() {
        return packetType;
    }

    public static Command getByType(byte type) {
        return typeToCmdMap.get(type);
    }
}
