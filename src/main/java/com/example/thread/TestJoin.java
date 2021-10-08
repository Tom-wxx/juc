package com.example.thread;

public class TestJoin implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("VIP来了。。。。。。"+i);
        }
    }


    public static void main(String[] args) throws InterruptedException {
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        for (int i = 0; i < 500; i++) {
            System.out.println("主线》》》"+i);
            if (i==50){

                thread.join();
            }
        }
    }
}
