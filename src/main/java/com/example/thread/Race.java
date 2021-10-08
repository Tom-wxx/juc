package com.example.thread;

public class Race implements Runnable{
    private static String winner;

    @Override
    public void run() {
        for (int i = 0; i < 800; i++) {
            if (Thread.currentThread().getName().equals("兔子")){
                try {
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
            //判断比赛是否结束
            boolean flag=gameOver(i);
            if (flag){
                break;
            }
            System.out.println(Thread.currentThread().getName()+"-->跑了"+i+"步");
        }
    }

    public boolean gameOver(int step){
        if (winner!=null){  //已经存在胜利者
            return true;
        }else {
            if (step>=100){
                winner= Thread.currentThread().getName();
                System.out.println("winner is"+winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race,"兔子").start();
        new Thread(race,"乌龟").start();
    }
}
