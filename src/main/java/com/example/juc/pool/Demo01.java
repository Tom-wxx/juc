package com.example.juc.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo01 {
    public static void main(String[] args) {
        /**
         * 线程池的三种方法：：：：底层就是重写ThredPoolExector方法*/
    //    ExecutorService threadPool = Executors.newSingleThreadExecutor();//单一线程
          ExecutorService threadPool = Executors.newFixedThreadPool(5); //固定线程池的大小
//       Executors.newCachedThreadPool();//可伸缩


        try {
            for (int i = 0; i < 10; i++) {
                //使用线程池来创建线程
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"  ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
    }
}
