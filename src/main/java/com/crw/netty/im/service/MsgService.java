package com.crw.netty.im.service;

import com.crw.netty.im.vo.GroupMsgVO;
import com.crw.netty.im.vo.SingleMsgVO;

/**
 * 消息服务
 */
public interface MsgService {

    /**
     * 单聊
     *
     * @param msg
     */
    void singleMsg(SingleMsgVO msg);

    /**
     * 群聊
     *
     * @param msg
     */
    void groupMsg(GroupMsgVO msg);
}
