package com.crw.netty.im.client;

import com.crw.netty.im.exception.ImException;

import java.util.Scanner;

/**
 * 客户端启动器
 */
public class ImClientStarter {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入用户名或者手机号:");
        String userName = scan.next();
        System.out.println("请输入密码:");
        String password = scan.next();

        ImClient imClient = null;
        try {
            imClient = new ImClient(userName, password);
            imClient.start();
            System.out.printf("用户 [%s] 登录成功", userName);
        } catch (ImException e) {
            System.err.println(e.getMessage());
        }

        while (scan.hasNext()) {
            String userId = scan.next();
            String text = scan.next();
            imClient.send(userId, text);
        }
        scan.close();
    }
}
