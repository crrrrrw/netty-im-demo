package com.crw.netty.im.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: dell
 * @Date: 2020/1/17 14:08
 * @Description:
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVO<T> implements Serializable {
    private static final long serialVersionUID = -8756759187661191696L;
    private String respCode;
    private String respMsg;
    private Integer dataLength;
    private T data;
}
