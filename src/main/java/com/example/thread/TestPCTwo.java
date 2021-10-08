package com.example.thread;

import lombok.SneakyThrows;

public class TestPCTwo {
    public static void main(String[] args) {
        TV tv = new TV();
        new Player(tv).start();
        new Watcher(tv).start();

    }
}

class TV{
    boolean flag=false;
    String voice;
    //表演
   public synchronized void play(String voice) throws InterruptedException {
       if (flag){
           this.wait();
       }
       System.out.println(voice+"：开播了！");
       //通知观众观看
       this.voice=voice;
       this.flag=!this.flag;
       this.notifyAll(); //通知唤起

   }
//观看
   public synchronized void watch() throws InterruptedException {
       if (!flag){
           this.wait();
       }
       System.out.println("观看了："+voice);
       //通知演员表演
       this.flag=!this.flag;
       this.notifyAll();

//       Thread.sleep(3000);
   }
}

class Player extends Thread{
    TV tv;
    public Player(TV tv){
        this.tv=tv;
    }
    @SneakyThrows
    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            if (i%2==0){
                tv.play("快乐大本营"+i);
            }else {
                tv.play("天天向上"+i);
            }
        }
    }
}

class Watcher extends Thread{
    TV tv;
    public Watcher(TV tv){
        this.tv=tv;
    }
    @SneakyThrows
    @Override
    public void run(){
        for (int i = 0; i < 20; i++) {
            tv.watch();
        }
    }
}
