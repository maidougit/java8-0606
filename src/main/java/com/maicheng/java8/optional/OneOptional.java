package com.maicheng.java8.optional;

import java.util.Optional;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: OneOptional.java
 * Author:   maidou
 * Date:     2018-06-14 11:10
 * Description:
 */
public class OneOptional {

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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getChampion() {
            return Champion;
        }

        public void setChampion(String champion) {
            Champion = champion;
        }
    }

    class Competition{
        private CompResult result;
        private String name;

        public CompResult getResult() {
            return result;
        }

        public void setResult(CompResult result) {
            this.result = result;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    class CompResult{
        private User champion;

        public User getChampion() {
            return champion;
        }

        public void setChampion(User champion) {
            this.champion = champion;
        }
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