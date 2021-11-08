package com.example.juc.function;

import java.util.function.Consumer;

/**消费型接口：：
 *  consumer   只有输入    没有返回值
 * */
public class ConsumerTest {
    public static void main(String[] args) {
        java.util.function.Consumer<String> objectConsumer = new java.util.function.Consumer<String>(){

            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        objectConsumer.accept("打印字符串");

        java.util.function.Consumer<String> consumer=(str)->{
            System.out.println(str);
        };

        consumer.accept("cccc");
    }
}
