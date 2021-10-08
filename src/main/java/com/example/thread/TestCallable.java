package com.example.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable implements Callable<Integer>{
    @Override
    public Integer call() throws Exception {
        int i = 0;
        for (i = 0; i < 500; i++) {
            System.out.println(Thread.currentThread().getName()+": "+i);
        }
        return i;
    }

    public static void main(String[] args) {
        TestCallable testCallable = new TestCallable();
        FutureTask<Integer> task1 = new FutureTask<>(testCallable);
        FutureTask<Integer> task2 = new FutureTask<>(testCallable);

        Thread th = new Thread(task1, "火车");
        Thread ths = new Thread(task2, "飞机");
        th.start();
        ths.start();
        try {
            System.out.println(th.getName()+task1.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


    }
}
