package com.crw.netty.im.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: dell
 * @Date: 2020/1/17 14:50
 * @Description:
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1976249776951291093L;
    private Long id;
    private String mobile;
    private String name;
    private String password;

}
