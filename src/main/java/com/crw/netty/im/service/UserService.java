package com.crw.netty.im.service;

import com.crw.netty.im.model.User;

import java.util.List;

/**
 * 用户服务
 */
public interface UserService {

    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    User get(Long userId);

    /**
     * 登录
     *
     * @param mobile
     * @param password
     * @return
     */
    User login(String mobile, String password);

    /**
     * 登出
     *
     * @param userId
     * @return
     */
    User logout(Long userId);

    /**
     * 好友列表
     *
     * @param userId
     * @return
     */
    List<User> friendList(Long userId);
}
