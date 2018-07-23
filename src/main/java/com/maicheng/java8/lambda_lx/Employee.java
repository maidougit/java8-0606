package com.maicheng.java8.lambda_lx;

/**
 * Copyright (C), 2017-2018, 上海阁为信息科技有限公司
 * FileName: Employee.java
 * Author:   lx
 * Date:     2018-07-21 11:42
 * Description: 员工
 */
public class Employee {

    private String name;

    private Integer age;

    private Double salary;


    public Employee() {
    }

    public Employee(String name, Integer age, Double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                '}';
    }


}
