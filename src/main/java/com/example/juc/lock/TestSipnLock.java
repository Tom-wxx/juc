package com.example.juc.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class TestSipnLock {
    public static void main(String[] args) throws InterruptedException {
        //原本的加锁解锁
     /*   ReentrantLock reentrantLock = new ReentrantLock();
        reentrantLock.lock();
        reentrantLock.unlock();*/

        //自己使用自旋锁cas实现的加锁 解锁
       /* SpinLock spinLock = new SpinLock();
        spinLock.myLock();
        spinLock.myUnLock();*/
        SpinLock lock = new SpinLock();

        new Thread(()->{
            lock.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }

        },"A").start();

            TimeUnit.SECONDS.sleep(1);

        new Thread(()->{
            lock.myLock();

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.myUnLock();
            }

        },"B").start();

        /**a 先拿到锁 所以不会自旋   b拿到锁因为a没有解锁所以会自旋
         * a解锁 后   b自旋结束  b解锁*/



    }
}
