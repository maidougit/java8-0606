package com.maicheng.java8.util;

import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 描述:java8代码重构
 *
 * FileName: FunctionUtil.java
 * @Author: maido
 * @date:    2018/7/25
 * @since v1.8.0
 */
public class FunctionUtil {
    public static <T,R> List<R> multiGetResult(List<Function<List<T>, R>> functions, List<T> list) {
        return functions.stream().map(f -> f.apply(list)).collect(Collectors.toList());
    }

    public static <K,R> List<R> mergeList(List<R> srcList, List<R> destList ,
                                          Function<R,K> keyFunc,
                                          BinaryOperator<R> mergeFunc) {
        return mergeList(srcList, destList, keyFunc, keyFunc, mergeFunc);
    }

    public static <T,S,K,R> List<R> mergeList(List<S> srcList, List<T> destList ,
                                              Function<S,K> skeyFunc, Function<T,K> dkeyFunc,
                                              BiFunction<S,T,R> mergeFunc) {
        return join(destList, mapKey(srcList, skeyFunc)).apply(dkeyFunc, (BiFunction) mergeFunc);

    }

    public static <T,K> Map<K,T> mapKey(List<T> list, Function<T,K> keyFunc) {
        return list.stream().collect(Collectors.toMap(keyFunc, t -> t, (k1,k2) -> k1));
    }

    public static <T,S,K,R> BiFunction<Function<T,K>, BiFunction<S,T,R>, List<R>> join(List<T> destList, Map<K,S> srcMap) {
        return (dkeyFunc,mergeFunc) -> destList.stream().map(
                dest -> {
                    K key = dkeyFunc.apply(dest);
                    S src = srcMap.get(key);
                    return mergeFunc.apply(src, dest);
                }).collect(Collectors.toList());
    }

    /** 对给定的值 x,y 应用指定的二元操作函数 */
    public static <T,S,R> Function<BiFunction<T,S,R>, R> op(T x, S y) {
        return opFunc -> opFunc.apply(x, y);
    }

    /** 将两个函数使用组合成一个函数，这个函数接受一个二元操作函数 */
    public static <T,S,Q,R> Function<BiFunction<S,Q,R>, R> op(Function<T,S> funcx, Function<T,Q> funcy, T x) {
        return opFunc -> opFunc.apply(funcx.apply(x), funcy.apply(x));
    }

    public static <T,S,Q,R> Function<BiFunction<S,Q,R>, Function<T,R>> op(Function<T,S> funcx, Function<T,Q> funcy) {
        return opFunc -> aT -> opFunc.apply(funcx.apply(aT), funcy.apply(aT));
    }

    /** 将两个函数组合成一个叠加函数, compose(f,g) = f(g) */
    public static <T> Function<T, T> compose(Function<T,T> funcx, Function<T,T> funcy) {
        return x -> funcx.apply(funcy.apply(x));
    }

    /** 将若干个函数组合成一个叠加函数, compose(f1,f2,...fn) = f1(f2(...(fn))) */
    public static <T> Function<T, T> compose(Function<T,T>... extraFuncs) {
        if (extraFuncs == null || extraFuncs.length == 0) {
            return x->x;
        }
        return x -> Arrays.stream(extraFuncs).reduce(y->y, FunctionUtil::compose).apply(x);
    }

    @Test
    public void test() {
        Console.log("结果:{}", multiGetResult(
                Arrays.asList(
                        list -> list.stream().collect(Collectors.summarizingInt(x->x)),
                        list -> list.stream().filter(x -> x < 50).sorted().collect(Collectors.toList()),
                        list -> list.stream().collect(Collectors.groupingBy(x->(x%2==0? "even": "odd"))),
                        list -> list.stream().sorted().collect(Collectors.toList()),
                        list -> list.stream().sorted().map(Math::sqrt).collect(Collectors.toMap(x->x, y->Math.pow(2,y)))),
                Arrays.asList(64,49,25,16,9,4,1,81,36)));
    }

    @Test
    public void test2() {
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        Supplier<Map<Integer,Integer>> mapSupplier = () -> list.stream().collect(Collectors.toMap(x->x, y-> y * y));

        Map<Integer, Integer> mapValueAdd = list.stream().collect(Collectors.toMap(x->x, y->y, (v1,v2) -> v1+v2, mapSupplier));
        Console.log("结果：{}",mapValueAdd);
    }

    @Test
    public void test3() {
        List<Integer> nums = Arrays.asList(Arrays.asList(1,2,3), Arrays.asList(1,4,9), Arrays.asList(1,8,27))
                .stream().flatMap(x -> x.stream()).collect(Collectors.toList());
        Console.log("结果：{}", nums);
    }

    @Test
    public void test4() {

        Console.log("结果：{}", op(new Integer(3), Integer.valueOf(3)).apply((x,y) -> x.equals(y.toString())));
    }

    @Test
    public void test5() {

        Console.log("结果：{}", op(x-> x.length(), y-> y+",world", "hello").apply((x,y) -> x+" " +y));
    }

    @Test
    public void test6() {

       Console.log("结果：{}", op(x-> x, y-> y+",world").apply((x,y) -> x+" " +y).apply("hello"));
    }

    @Test
    public void test7() {

        Console.log("结果：{}", op(x-> x.toString().length(), y-> y+",world").apply((x,y) -> x+" " +y).apply("hello"));
    }

    @Test
    public void test8() {
        Console.log("结果：{}", mergeList(Arrays.asList(1,2), Arrays.asList("an", "a"),
                s-> s, t-> t.toString().length(), (s,t) -> s+t));
    }
}