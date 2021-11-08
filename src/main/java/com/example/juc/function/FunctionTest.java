package com.example.juc.function;

import java.util.function.Function;

/**函数式接口：
 *     只有一个方法的接口
 *     */
public class FunctionTest {
    public static void main(String[] args) {

        /**
         * 有一个输入参数   有一个输出参数
         * 只要是函数式接口就可以使用lambda简化
         * */
        Function function = new Function<String,String>() {

            @Override
            public String apply(String str) {
                return str;
            }
        };


        System.out.println(function.apply("123"));

        //lambda
        Function<String,String> function1=(str)->{
            return str;
        };
        System.out.println(function1.apply("www"));
    }
}
