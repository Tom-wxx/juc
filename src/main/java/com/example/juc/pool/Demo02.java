package com.example.juc.pool;

import sun.nio.ch.ThreadPool;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Demo02 {
    public static void main(String[] args) {
        /**
         * 自定义线程池
         *
         * 最大线程数调优：1.CPU密集型  查看电脑是几核cpu  00、、就定义为几  保证cpu效率最高  代码获取cpu核数 Runtime。getruntime。availableProcessors
         *              2.io密集型
         * */
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,      //正常开放的线程数     核心线程数
                5,   //阻塞队列已满，但还有线程要进来，就开启最大线程数，释放队列空间，达到了最大并发量
                3,     //超时等待：空闲线程存活时间，对等待区（最大线程0有效） 对核心线程无效   等待3秒钟
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(3),  //线程开始进入队列，但是队列里的线程也会出去，达到核心线程数，如果还有线程进入，队列可能就满了，然后开启最大数
                Executors.defaultThreadFactory(),   //开启默认线程工厂  一般就这个
                new ThreadPoolExecutor.AbortPolicy()  //拒绝策略  有4种 此时最大并发量已满，阻塞队列已满，就执行此方法   （线程创建数量不能大于最大线程 + 阻塞队列数）
        );

        /**使用线程池的话，就用线程池创建
         * 最大承载  Deque+max   超出就抛出异常*/

        try {
            for (int i = 0; i < 7; i++) {
                /**使用lambod表达式
                 * 在里面执行命令等     输出信息*/
                threadPoolExecutor.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"  ok");
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPoolExecutor.shutdown();
        }

    }
}
