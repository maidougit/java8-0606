package com.lx.java8.lambda_lx;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * FileName: Employee.java
 * Author:   lx
 * Date:     2018-07-21 11:42
 * Description: 员工
 */
@Data
public class Employee {

    private String name;

    private Integer age;

    private Double salary;

    private Status status;


    public Employee() {
    }

    public Employee(Integer age) {
        this.age = age;
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public Employee(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Employee(String name, Integer age, Double salary, Status status) {
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.status = status;
    }

    @Override
    public String toString() {

        return ReflectionToStringBuilder.reflectionToString(this);
    }

    public enum Status {
        FREE,
        BUSY,
        VOCATION,
        FIRED;
    }

}
