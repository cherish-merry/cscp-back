package com.cscp.common.utils;

import java.util.UUID;

/**
 * @author chen kezhuo
 * @discription
 * @date 2020/3/28 - 21:37
 */
public class UUIDGenerator {
    private static Integer num=5;

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            System.out.println(UUID.randomUUID().toString());
        }
    }
}
