package com.example.juc.sign;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**懒汉式单例模式*/
public class LazyMan {

    //没有直接使用
    private volatile  static LazyMan lazyMan;

    /**只要是单例就构造器私有*/
    private LazyMan(){
//可以在这里写
        //System.out.println(Thread.currentThread().getName()+"  ok");
        //验证反射
        synchronized (LazyMan.class){
            if (lazyMan!=null){
                throw new RuntimeException("不要用反射破坏异常");
            }
        }
    }

    //此时的单线程可以使用，多线程可以使用双重检查锁
    /*public static LazyMan getLazyMan(){
        if (lazyMan==null){
            lazyMan=new LazyMan();
        }
        return lazyMan;
    }*/
    public static LazyMan getLazyMan(){
        if (lazyMan==null){
            synchronized (LazyMan.class){  //锁住这个class类
                if (lazyMan==null){
                    lazyMan=new LazyMan();  //再执行就可以出现单例  一个线程了    不是原子性操作 底层三步操作
                    /**1. 分配内存空间
                     * 2.执行构造方法，初始化对象
                     * 3.把这个对象指向这个空间
                     *
                     * 可能出现指令重排现象  我们期望出现123  但是出现321
                     * 就可以写成这样   private volatile  static LazyMan lazyMan; */
                }
            }
        }
        return lazyMan;
    }

    //单线程下是可以的   多线程下出错  出现多个

    public static void main(String[] args) throws Exception {
       /* for (int i = 0; i < 10; i++) {
            new Thread(()->{
                LazyMan.getLazyMan();
            }).start();
        }*/

        //反射会破坏单例
        LazyMan lazyMan = LazyMan.getLazyMan(); //先创建一个
        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null); //得到一个空的构造器
        declaredConstructor.setAccessible(true);  //不是私有的构造器 破坏私有权限
        LazyMan lazyMan1 = declaredConstructor.newInstance();  //反射创建对象

        System.out.println(lazyMan);
        System.out.println(lazyMan1);
        /**不一样了
         * com.example.juc.sign.LazyMan@23fc625e
           com.example.juc.sign.LazyMan@3f99bd52*/

    }
}
