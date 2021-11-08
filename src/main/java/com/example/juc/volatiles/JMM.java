package com.example.juc.volatiles;

import java.util.concurrent.TimeUnit;

/**
 * volatile  特性
 * 保证可见性
 * 不保证原子性  原子性就是不可分割
 * 由于内存屏障 可以 避免指令重排*/

public class JMM {
    //不加 volatile 程序会死循环 因为线程一感知不到 num 被main线程修改了 没有可见性
    private volatile static int num=0;

    public static void main(String[] args) {  //线程main

        new Thread(()->{    //线程一
            while (num==0){ }
        }).start();
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            num=1;
        System.out.println(num);
    }
}
