package com.example.juc.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo02 {
    public static void main(String[] args) {

        Phone2 phone = new Phone2();

        phone.sms();
        new Thread(()->{
            phone.sms();
        },"A").start();
        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone2{
    Lock lock=new ReentrantLock();
    public  void sms(){
        lock.lock();   //细节问题：  相当于拿到了两把锁一个是外边sms的锁 一个是call里面的锁   而synchronized是一把锁   锁必须配对否则会死在里面
        try {
            System.out.println(Thread.currentThread().getName()+"======sms");
            call(); //这里也有锁
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }
    public synchronized void call() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "======call");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
