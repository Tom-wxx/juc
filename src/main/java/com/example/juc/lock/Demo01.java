package com.example.juc.lock;
/** 可重入锁（递归锁） ： 拿到外面的锁之后，就可以以拿到里面的锁，自动获得
 *main======sms    main======call
 * A======sms     A======call
 * B======sms     B======call
 *  包含的关系  拿到外面的锁之后会自动拿到里面的锁*/
//synchronized
public class Demo01 {
    public static void main(String[] args) {

        Phone phone = new Phone();

        phone.sms();
        new Thread(()->{
            phone.sms();
        },"A").start();
        new Thread(()->{
            phone.sms();
        },"B").start();
    }
}

class Phone{
    public synchronized void sms(){
        System.out.println(Thread.currentThread().getName()+"======sms");
        call(); //这里也有锁
    }
    public synchronized void call(){
        System.out.println(Thread.currentThread().getName()+"======call");
    }
}
