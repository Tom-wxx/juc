package com.example.juc.volatiles;


import java.util.concurrent.atomic.AtomicInteger;

/**验证是否具有原子性*/
public class Demo02 {
    //除了添加lock和  保证原子性   volatile不能保证原子性
  //  private volatile static int num=0;

    //还可以使用原子类d integer
    private volatile static AtomicInteger num=new AtomicInteger();

    public static void add(){
      //  num++; 使用原子类的话有具体的方法
        num.getAndIncrement();   //加一方法   底层是cas
    }
    public static void main(String[] args) {

        //理论上位2万
        for (int i = 0; i < 20; i++) {
            new Thread(()->{
                for (int i1 = 0; i1 < 1000; i1++) {
                    add();
                }
            }).start();
        }

        while (Thread.activeCount()>2){  //当前线程大于2的时候礼让  main gc
            Thread.yield();  //把main线程礼让给其他线程，等其他线程死亡再执行main线程
        }
        System.out.println(Thread.currentThread().getName()+"   "+num);
    }
}
