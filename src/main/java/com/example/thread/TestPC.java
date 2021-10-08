package com.example.thread;

import lombok.SneakyThrows;

//测试生产者消费者   利用缓冲区
public class TestPC {
    public static void main(String[] args) {
        SynContainer synContainer = new SynContainer();

        new Productor(synContainer).start();
        new Consumer(synContainer).start();
    }
}

//生产者
class Productor extends Thread{
  SynContainer container;
  public Productor(SynContainer container){
      this.container=container;
  }
  //生产方法
    @SneakyThrows
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            container.push(new Chicken(i));
            System.out.println("生产了===="+i+"只鸡");
        }
    }
}
//消费者
class Consumer extends Thread{
    SynContainer container;
    public Consumer(SynContainer container){
        this.container=container;
    }
    //消费方法
    @SneakyThrows
    @Override
    public void run(){
        for (int i = 0; i < 100; i++) {
            System.out.println("消费了===="+container.pop().i+"只鸡");
        }
    }
}
//产品
class Chicken{
     int i;
    public Chicken(int i) {
        this.i=i;
    }
}
//缓冲区
class  SynContainer{
    //需要一个容器大小
    Chicken[] chickens=new Chicken[10];
    //容器计数器
    int count=0;
    //生产者生产产品   放入容器
    public synchronized void push(Chicken chicken) throws InterruptedException {
        //如果容器满了，需要等待消费者消费
        if (count==chickens.length ){
            //通知消费者等待
            this.wait();
        }
        //如果没有满，需要丢入产品
        chickens[count]=chicken;
        count++;
        //可以通知消费者
        this.notifyAll();
        //Thread.sleep(1000);
    }
    //消费者消费产品
    public synchronized Chicken pop() throws InterruptedException {
        //判断是否可以消费
        if(count==0){
            //等待生产者生产
            this.wait();
        }
        //可以消费
        count--;
        Chicken chicken = chickens[count];
        //吃完了，通知生产
        this.notifyAll();
        Thread.sleep(2000);
        return chicken;
    }
}