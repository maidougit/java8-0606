package com.maicheng.java8.optional;

import lombok.Data;

import java.util.Optional;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: OneOptional.java
 * Author:   maidou
 * Date:     2018-06-14 11:10
 * Description:
 */

public class OneOptional {

    @Data
    class User{
        private String name;

        private int age;

        private String Champion;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public User() {
        }
    }

    @Data
    class Competition{
        private CompResult result;
        private String name;
    }

    @Data
    class CompResult{
        private User champion;
    }

    public static void main(String[] args) {
        //创建Optional对象
        Optional<String> opt = Optional.empty();

        System.out.println("结果--->" + opt);

        //依据一个非空值创建Optional
        Optional<String> opt1 = Optional.of("hello");

       //可接受null的Optional
        Optional<String> opt2 = Optional.ofNullable(null);
    }

    public static String getName(User u) {
        if (u == null)
            return "Unknown";
        return u.name;
    }

    public static String getName1(User u) {
        Optional<User> user = Optional.ofNullable(u);
        if (!user.isPresent())
            return "Unknown";
        return user.get().name;
    }

    public static String getName2(User u) {
        return Optional.ofNullable(u)
                .map(user->user.name)
                .orElse("Unknown");
    }

    public static String getChampionName(Competition comp) throws IllegalArgumentException {
        if (comp != null) {
            CompResult result = comp.getResult();
            if (result != null) {
                User champion = result.getChampion();
                if (champion != null) {
                    return champion.getName();
                }
            }
        }

        throw new IllegalArgumentException("The value of param comp isn't available.");
    }

    public static String getChampionName1(Competition comp) throws IllegalArgumentException {
        return Optional.ofNullable(comp)
                .map(c->c.getResult())
                .map(r->r.getChampion())
                .map(u->u.getName())
                .orElseThrow(()->new IllegalArgumentException("The value of param comp isn't available."));
    }
}