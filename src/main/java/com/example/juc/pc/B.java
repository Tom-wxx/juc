package com.example.juc.pc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class B {
    public static void main(String[] args) {
        Data2 data2 = new Data2();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.increment();
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                data2.decrement();
            }
        },"D").start();
    }
}

class Data2{
    private int number=0;
    Lock lock=new ReentrantLock();   //替代了synchronized
    Condition condition = lock.newCondition();  //替代了里面的方法

    public void increment(){
        lock.lock();




        try {
            while (number!=0){
                condition.await();
            }
            ++number;
            System.out.println(Thread.currentThread().getName()+":  "+number);
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement(){
        lock.lock();
        try {
            while (number==0){
                condition.await();
            }
            --number;
            System.out.println(Thread.currentThread().getName()+":  "+number);
            condition.signalAll();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }


}
