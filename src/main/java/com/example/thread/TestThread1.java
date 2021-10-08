package com.example.thread;


import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class TestThread1 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步");
        }
    }

    public static void main(String[] args) {
        TestThread1 myRun = new TestThread1();
        Thread thread1 = new Thread(myRun, "飞机");
        Thread thread2 = new Thread(myRun, "高铁");
        thread1.start();
        thread2.start();
        System.out.println("=====================================================");

    }
}

