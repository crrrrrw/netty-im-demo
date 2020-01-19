package com.crw.netty.im.codec;

import io.netty.channel.CombinedChannelDuplexHandler;

public class PacketCodec extends CombinedChannelDuplexHandler<PacketDecoder, PacketEncoder> {
    public PacketCodec() {
        super(new PacketDecoder(), new PacketEncoder());
    }
}
