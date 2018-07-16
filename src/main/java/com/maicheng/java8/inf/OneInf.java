package com.maicheng.java8.inf;

/**
 * FileName: OneInf.java
 * Author:   maidou
 * Date:     2018-06-14 10:26
 * Description:
 * 默认方法出现的原因是为了对原有接口的扩展，有了默认方法之后就不怕因改动原有的接口而对已经使用这些接口的程序造成的代码不兼容的影响。 在Java8中也对一些接口增加了一些默认方法，比如Map接口等等。一般来说，使用默认方法的场景有两个：可选方法和行为的多继承。

 * 默认方法的使用相对来说比较简单，唯一要注意的点是如何处理默认方法的冲突。关于如何处理默认方法的冲突可以参考以下三条规则：

 * 类中的方法优先级最高。类或父类中声明的方法的优先级高于任何声明为默认方法的优先级。
 * 如果无法依据第一条规则进行判断，那么子接口的优先级更高：函数签名相同时，优先选择拥有最具体实现的默认方法的接口。即如果B继承了A，那么B就比A更具体。
 * 最后，如果还是无法判断，继承了多个接口的类必须通过显式覆盖和调用期望的方法，显式地选择使用哪一个默认方法的实现。那么如何显式地指定呢:
 */
 interface OneInf {

    double calculate(int a);

    default double sqrt(int a){

        return Math.sqrt(a);
    }

    static void print(){

        System.out.println("我是接口");
    };

     static void main(String[] args) {
        OneInf oneInf = new Message();

        System.out.println("计算结果：{}" + oneInf.calculate(12));

        OneInf.print(); //接口直接调用
    }

}

class Message implements OneInf{

    @Override
    public double calculate(int a) {

        return sqrt(a);
    }
}
