package com.example.juc.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
    public static void main(String[] args) {
       // new Thread(new Runnable()).start();
        /*FutureTask 为 Runnable 实现类  所以可以放入Thread里*/
        MyThread myThread = new MyThread();
        //new Thread(new FutureTask<String>(myThread)).start();
        FutureTask futureTask = new FutureTask(myThread);  //适配类
        new Thread(futureTask).start();
    }
}

class MyThread implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "nsj";
    }
}