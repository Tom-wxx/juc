package com.example.juc.add;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;


//加法计数器
public class CyclicBarrierDemo {
    public static void main(String[] args) {
        /*CyclicBarrier达到特定线程数就会运行lambda*/
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{ System.out.println("召唤神龙"); });

        for (int i = 1; i <= 7; i++) {
            final int temp=i;
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"收集"+temp+"个龙珠。");
                try {
                    cyclicBarrier.await();  //等待
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
