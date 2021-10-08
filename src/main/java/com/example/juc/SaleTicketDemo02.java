package com.example.juc;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket2 = new Ticket2();
        new Thread(()-> { for (int i = 0; i <40 ; i++) ticket2.sale();},"A").start();
        new Thread(()-> { for (int i = 0; i <40 ; i++) ticket2.sale();},"B").start();
        new Thread(()-> { for (int i = 0; i <40 ; i++) ticket2.sale();},"C").start();
    }
}
//lock锁
class Ticket2{
    //属性，方法
    private int number=30;
    Lock lock=new ReentrantLock();

    public void sale(){
        lock.lock(); //加锁
        try {
            //业务代码
            if (number>0){
                System.out.println(Thread.currentThread().getName());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock(); //解锁
        }

    }

}