package com.example.juc.bq;

import java.util.Collection;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 队列的形式  FIFO
 * 先进先出*/

public class Test  {
    public static void main(String[] args) {

        /**阻塞队列：blockingQueue
         * 非阻塞队列：AbstractQueue
         * 双端队列：Deque
         * queue继承collection*/


        test2();

    }

    /**使用方法为add   remove
     * 抛出异常
     * 检测队首元素：element*/
    public static void test1(){
      /**  此为抛出异常
       *  队列的大小，存入数字就可以指定队列有几个空间
       *  如果填入超过设置的大小，会报错
       *  */
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        //添加
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        /**第四个就会抛出异常
         * IllegalStateException: Queue full  ： 队列已满*/
        System.out.println(blockingQueue.add("c"));

        //移除 remove      道理同上

    }

   /** 不抛出异常，只返回true或false    使用方法为offer   poll   返回null
    * 检测队首元素：peek
    * 有返回值
    * */
    public static void test2(){
        /**  此为抛出异常
         *  队列的大小，存入数字就可以指定队列有几个空间
         *  如果填入超过设置的大小，会报错
         *  */
        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        //添加
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        /**第四个就会抛出异常
         * IllegalStateException: Queue full  ： 队列已满*/
        System.out.println(blockingQueue.offer("c"));
        //blockingQueue.offer("e",2, TimeUnit.SECONDS);  //等待2秒，超时退出

        //移除 poll      道理同上

    }



    /**等待，阻塞（一直阻塞，直 到有空位置为止）
     * */
    public static void test3() throws InterruptedException {

        BlockingQueue<Object> blockingQueue = new ArrayBlockingQueue<>(3);
        //添加
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c ");
        blockingQueue.put("d");  //队列没有位置了，一直阻塞  put无返回值

        //移除 take      道理同上  有返回值

    }


}
