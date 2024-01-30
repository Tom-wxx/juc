package com.example.threadlock;

/**
 * ClassName: ThreadLockDemo
 * Package: com.example.threadlock
 * Description:
 * 保存每个线程独享的对象：当需要为每个线程保存一个独立对象时，可以使用ThreadLocal。
 *    例如，当一个线程需要访问数据库连接时，可以为每个线程创建一个独立的数据库连接，并将它们保存在ThreadLocal中，以便后续方法可以直接获取和使用。
 * 保存线程内独立的信息：当每个线程需要独立保存信息时，可以使用ThreadLocal。
 *    例如，当一个线程需要记录日志时，可以将日志信息保存在ThreadLocal中，以便后续方法可以直接获取和使用。
 * 替代参数的显式传递：
 *   在编写API接口时，如果接口功能比较复杂，可能需要调用Service层内部的其他方法。
 *   通常情况下，会在每个调用的方法上加上需要传递的参数。但是，如果将参数存入ThreadLocal中，那么就不用显式的传递参数了，而是只需要ThreadLocal中获取即可。
 *
 * @Author: wxx
 * @Create: 2023/12/13 - 10:47
 */
public class ThreadLockDemo {

    /**
     * 创建一个ThreadLocal变量来保存线程内的信息
     */
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        //TODO 创建多个线程，每个线程执行不同的任务
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                //TODO 在线程内设置ThreadLocal变量的值
                threadLocal.set("Thread " + Thread.currentThread().getId());
                //TODO 执行任务1
                doTask1();
                //TODO 执行任务2
                doTask2();
            }).start();
        }
    }

    public static void doTask1() {
        //TODO 从ThreadLocal变量中获取线程内的信息
        String threadName = threadLocal.get();
        System.out.println("Task 1: " + threadName);
    }

    public static void doTask2() {
        //TODO 从ThreadLocal变量中获取线程内的信息
        String threadName = threadLocal.get();
        System.out.println("Task 2: " + threadName);
    }

}
