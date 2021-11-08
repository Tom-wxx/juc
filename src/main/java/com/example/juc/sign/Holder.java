package com.example.juc.sign;


//静态内部类
public class Holder {
    private Holder(){

    }

    public static Holder getInstance(){
        return InnerHolder.holder;
    }


    public static class InnerHolder{

        private final static Holder holder=new Holder();
    }
}
