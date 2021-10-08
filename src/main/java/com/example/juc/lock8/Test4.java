package com.example.juc.lock8;

import java.util.concurrent.TimeUnit;
/**
 * 7、1个静态的同步方法，1个普通的同步方法 ，一个对象，先打印 发短信？打电话？
 * 8、1个静态的同步方法，1个普通的同步方法 ，两个对象，先打印 发短信？打电话？
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象的Class类模板只有一个，static，锁的是Class
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

        // 锁的存在
        new Thread(()->{phone1.sendSms();},"A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()->{phone2.call();},"B").start();


    }
}

class Phone4 {


    // 静态的同步方法 锁的是 Class 类模板
    public static synchronized void sendSms() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }
    // 普通的同步方法  锁的调用者(对象),二者锁的对象不同,所以不需要等待
    public  synchronized void call() {
        System.out.println("打电话");
    }

}

// 7/8 两种情况下，都是先执行打电话,后执行发短信,因为二者锁的对象不同,
// 静态同步方法锁的是Class类模板,普通同步方法锁的是实例化的对象,
// 所以不用等待前者解锁后 后者才能执行,而是两者并行执行,因为发短信休眠4s
// 所以打电话先执行。
