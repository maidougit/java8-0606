package com.maicheng.java8.predicate;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: Apple.java
 * Author:   hyz
 * Date:     2018-06-13 17:34
 * Description:
 */
public class Apple {

    private int getWeight;

    private String color;

    public Apple(int getWeight, String color) {
        this.getWeight = getWeight;
        this.color = color;
    }

    public Apple(int getWeight) {
        this.getWeight = getWeight;
    }

    public Apple() {
    }

    public int getGetWeight() {
        return getWeight;
    }

    public void setGetWeight(int getWeight) {
        this.getWeight = getWeight;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}