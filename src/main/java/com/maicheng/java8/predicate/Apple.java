package com.maicheng.java8.predicate;

import lombok.Data;

/**
 * 描述:TODO
 *
 * FileName: Apple.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
 */
@Data
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
}