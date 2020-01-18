package com.crw.netty.im.vo;

/**
 */
public enum Command {

    LOGIN((byte) 1, "login"),
    LOGOUT((byte) 2, "logout"),
    P2P_MSG((byte) 3, "p2pMsg"),
    GROUP_MSG((byte) 4, "groupMsg");

    Command(Byte type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    private Byte type;
    private String desc;

}
