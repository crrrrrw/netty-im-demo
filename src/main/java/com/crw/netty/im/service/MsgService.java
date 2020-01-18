package com.crw.netty.im.service;

import com.crw.netty.im.protocal.GroupMsgPacket;
import com.crw.netty.im.protocal.SingleMsgPacket;

/**
 * 消息服务
 */
public interface MsgService {

    /**
     * 单聊
     *
     * @param msg
     */
    void singleMsg(SingleMsgPacket msg);

    /**
     * 群聊
     *
     * @param msg
     */
    void groupMsg(GroupMsgPacket msg);
}
