package com.example.juc.lock;

import java.util.concurrent.TimeUnit;

/**死锁：相互调用对方的资源，形成僵持
 *
 * 排查解决问题   死锁排查
 *  1.  使用jps -l定位进程号    13400 com.example.juc.lock.DeadLockDemo
 *  2.  使用jstack 进程号  查看进程信息 ：如 jstack 13400      Found 1 deadlock寻找到一个死锁
 *
 *  面试 工作中 排查问题
 *    1.进程卡了：看看有没有什么线上异常问题
 *    2.查看日志信息，有没有问题
 *    3.查看堆栈信息*/
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA="lockA";
        String lockB="lockB";

        new Thread(new MyThread(lockA,lockB),"T1").start();
        new Thread(new MyThread(lockB,lockA),"T1").start();
    }
}

class MyThread implements Runnable{

    private String lockA;
    private String lockB;

    public MyThread(String lockA,String lockB){
        this.lockA=lockA;
        this.lockB=lockB;
    }

    @Override
    public void run() {
        synchronized (lockA){
            System.out.println(Thread.currentThread().getName()+"   lock=="+lockA+"   get=="+lockB);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lockB){
                System.out.println(Thread.currentThread().getName()+"  lock=="+lockB+"   get=="+lockA);

            }
        }

    }
}
