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
public class GroupMsgVO implements Serializable {

    private static final long serialVersionUID = -4640361210425910281L;
    private Long userId;
    private Long groupId;
    private String msg;
}
