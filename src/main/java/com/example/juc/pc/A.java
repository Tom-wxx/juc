package com.example.juc.pc;

public class A {
    public static void main(String[] args) {
        Data data=new Data();
        /*
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
        */

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try {
                    data.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        /*new Thread(()->{
            try {
                data.increment();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"C").start();
        new Thread(()->{
            try {
                data.decrement();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"D").start();*/

    }
}

class Data{
    private int number=10;

    public synchronized void increment() throws InterruptedException {
        //使用if判断，当使用多个线程进行的时候会出现虚假唤醒
        if (number!=0){
            this.wait();
        }

       /* while (number!=0){
            this.wait();
        }*/

        ++number;
        System.out.println(Thread.currentThread().getName()+": "+number);
        this.notifyAll();
        //Thread.sleep(1000);
    }

    public synchronized void decrement() throws InterruptedException {
        if (number==0){
            this.wait();
        }

       /* while (number==0){
            this.wait();
        }*/

        --number;
        System.out.println(Thread.currentThread().getName()+": "+number);
        this.notifyAll();
        //Thread.sleep(2000);
    }
}
