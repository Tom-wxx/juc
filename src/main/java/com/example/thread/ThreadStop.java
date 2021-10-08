package com.example.thread;

public class ThreadStop implements Runnable{
    private  boolean flag=true;
    @Override
    public void run() {
        int i=0;
        while (flag){
            System.out.println("run....thread::"+i++);
        }
    }
    public void stop(){
        this.flag=false;
    }

    public static void main(String[] args) {
        ThreadStop threadStop = new ThreadStop();
        new Thread(threadStop).start();
        for (int i = 0; i < 100; i++) {
            System.out.println("main::"+i);
            if (i==97){
                threadStop.stop();
                System.out.println("线程停止");
            }
        }
    }
}
