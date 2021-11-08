package com.example.juc.function;

import java.util.function.Predicate;

/**
 * 断定型接口：：：
 * 有一个输入参数      返回值是boolean */
public class PredicateTest {
    public static void main(String[] args) {

        /**
         * 可以写判断字符串是否为空*/
        Predicate<String> predicate = new Predicate<String>() {

            @Override
            public boolean test(String s) {
                return s.isEmpty();
            }
        };

        System.out.println(predicate.test("4545"));

        Predicate<String> predicate1=(str)->{
            return str.isEmpty();
        };
        System.out.println(predicate1.test(""));
    }
}
