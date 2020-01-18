package com.crw.netty.im.service;

import com.crw.netty.im.model.User;
import com.crw.netty.im.store.EmbeddedDB;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmbeddedUserServiceImpl implements UserService {
    @Override
    public User get(Long userId) {
        return EmbeddedDB.userDB.get(userId);
    }

    @Override
    public User login(String mobile, String password) {
        List<User> users = EmbeddedDB.userDB.entrySet().stream()
                .filter(entry -> entry.getValue().getMobile().equals(mobile))
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());

        //TODO 发消息

        return users.isEmpty() ? null : users.get(0);
    }

    @Override
    public User logout(Long userId) {
        //TODO 发消息

        return null;
    }

    @Override
    public List<User> friendList(Long userId) {
        List<Long> ids = EmbeddedDB.friendsDB.get(userId);
        List<User> friendList = new ArrayList<>();
        ids.forEach(id -> friendList.add(EmbeddedDB.userDB.get(id)));
        return friendList;
    }
}
