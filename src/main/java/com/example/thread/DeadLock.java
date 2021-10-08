package com.example.thread;

import lombok.SneakyThrows;

public class DeadLock {
    public static void main(String[] args) {
        MakeUp wxx = new MakeUp(0,"wxx");
        MakeUp sxx = new MakeUp(1, "sxx");
        Thread thread1=new Thread(sxx);
        Thread thread = new Thread(wxx);
        thread.start();
        thread1.start();
    }
}

class A{ }
class B{ }
class MakeUp implements Runnable{

    static A a=new A();
    static B b=new B();

    int count;
    String name;
    public MakeUp(int count,String name){
        this.count=count;
        this.name=name;
    }


    @SneakyThrows
    @Override
    public void run() {
       makeUp();

    }

    private  void  makeUp() throws InterruptedException {
        if (count==0){
            synchronized (a){
                System.out.println(this.name+"===A");
                Thread.sleep(1000);
            }
            synchronized (b){
                System.out.println(this.name+"===B");
            }
        }else {
            synchronized (b){
                System.out.println(this.name+"===B");
                Thread.sleep(2000);
            }
            synchronized (a){
                System.out.println(this.name+"===A");
            }
        }
    }
}