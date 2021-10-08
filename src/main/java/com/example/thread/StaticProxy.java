package com.example.thread;

public class StaticProxy {
    public static void main(String[] args) {

        new Thread(()-> System.out.println("123")).start();
        // new 代理对象(new 实现类()).方法();
        new weddingCompany(new You()).happyMarry();
    }
}

interface Marry{
    void happyMarry();
}

//真实的对象
class You implements Marry{
    @Override
    public void happyMarry(){
        System.out.println("you marry !!");
    }
}

//代理的对象
class weddingCompany implements Marry{

    private Marry target;
    public weddingCompany(Marry target){
        this.target=target;
    }
    @Override
    public  void  happyMarry(){
        befor();
        this.target.happyMarry();//真实角色
        after();
    }
    private void befor(){
        System.out.println("befor.............");
    }
    private void after(){
        System.out.println("ending............");
    }
}
