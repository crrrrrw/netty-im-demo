package com.crw.netty.im.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SingleMsgVO implements Serializable {
    private static final long serialVersionUID = -1302678252721280433L;
    private Long fromUserId;
    private Long destUserId;
    private String msg;
}
