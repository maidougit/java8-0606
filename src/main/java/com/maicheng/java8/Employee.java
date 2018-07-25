/**
 * Copyright 2018 springboot
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.maicheng.java8;

import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.Objects;

/**
 * Copyright (C), 2017-2018, maicheng
 * FileName: Employee.java
 * Author:   maido
 * Date:     2018/7/25 14:13
 * @return :         
 * Description:     
 */
@Data
public class Employee {

    private Integer id;

    private double salary;

    private String name;

    private Integer age;

    public Employee() {
    }

    public Employee(Integer id, double salary, String name, Integer age) {
        this.id = id;
        this.salary = salary;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Double.compare(employee.salary, salary) == 0 &&
                Objects.equals(id, employee.id) &&
                Objects.equals(name, employee.name) &&
                Objects.equals(age, employee.age);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, salary, name, age);
    }

    @Override
    public String toString() {

        return ReflectionToStringBuilder.reflectionToString(this);
    }
}
