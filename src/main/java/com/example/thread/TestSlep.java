package com.example.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
//模拟延时，放大问题的发生性
public class TestSlep {
    //模拟倒计时
    public static void countDowm() throws InterruptedException {
        int num=10;
        while (num!=0){
            Thread.sleep(1000);
            System.out.println(num--);
        }
    }

    //打印当前时间
    public static void dateDown(){
        Date date = null;
        while (true){
            try {
                Thread.sleep(1000);
                date=  new Date(System.currentTimeMillis());
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(date));
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) throws InterruptedException {
        //countDowm();
        dateDown();
    }
}
