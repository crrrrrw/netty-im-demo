package com.crw.netty.im.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

/**
 * @Auther: dell
 * @Date: 2020/1/17 15:07
 * @Description:
 */
public class FastJsonCodeC extends CombinedChannelDuplexHandler<FastJsonDecoder, FastJsonEncoder> {

    public FastJsonCodeC() {
        super(new FastJsonDecoder(), new FastJsonEncoder());
    }

}







