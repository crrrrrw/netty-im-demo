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
public class RequestVO<T> implements Serializable {
    private static final long serialVersionUID = -6792176522392591694L;
    /**
     * 请求Id
     */
    private String reqId;
    /**
     * 命令
     */
    private Byte command;
    /**
     * 数据长度
     */
    private Integer dataLength;
    /**
     * 数据
     */
    private T data;
}
