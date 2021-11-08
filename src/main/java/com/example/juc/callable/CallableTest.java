package com.example.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
/**
 * callable 相比较与runnable 1.可以有南丰汇值
 *                          2.可以抛出异常
 *                          3.方法不同：runnable   run： callable  call */

public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException { //抛出异常
    // new Thread(new Runnable()).start();
    // new Thread(new FutureTask<V>()).start();
    // new Thread(new FutureTask<V>(Callable)).start();

        /*FutureTask 为 Runnable 实现类  所以可以放入Thread里*/
        MyThread myThread = new MyThread();
        //重点    new Thread(new FutureTask<String>(myThread)).start();
        FutureTask futureTask = new FutureTask(myThread);  //适配类
        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();  //结果会被作为缓存，效率高

        String o = (String)futureTask.get();  //get方法可能产生阻塞，把他放在最后
        System.out.println(o);
    }
}

class MyThread implements Callable<String> {  //这里的string为下面的返回类型

    @Override
    public String call() throws Exception {  //string
        return "nsj";
    }
}