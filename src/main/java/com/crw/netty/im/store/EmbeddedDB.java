package com.crw.netty.im.store;

import com.crw.netty.im.model.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 内存数据库
 */
public class EmbeddedDB {

    public static ConcurrentHashMap<Long, User> userDB = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<Long, List<Long>> friendsDB = new ConcurrentHashMap<>();

    static {
        userDB.put(1L, User.builder().name("张三").password("123456").build());
        userDB.put(2L, User.builder().name("李四").password("123456").build());
        userDB.put(3L, User.builder().name("王五").password("123456").build());

        friendsDB.put(1L, Stream.of(2L, 3L).collect(Collectors.toList()));
        friendsDB.put(2L, Stream.of(1L, 3L).collect(Collectors.toList()));
        friendsDB.put(3L, Stream.of(1L, 2L).collect(Collectors.toList()));
    }
}
