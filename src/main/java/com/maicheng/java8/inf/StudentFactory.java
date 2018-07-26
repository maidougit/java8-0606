package com.maicheng.java8.inf;

import com.maicheng.java8.model.Student;

/**
 * 描述:TODO
 *       
 * FileName: StudentFactory.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
 */
public interface StudentFactory {

    Student create(String name, Integer age);
}