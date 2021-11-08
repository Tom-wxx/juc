package com.example.juc.lock;

import java.util.concurrent.atomic.AtomicReference;

/**自旋锁 ：不断的循环遍历迭代  不断地尝试知道成功为止*/
public class SpinLock {
    //用原子引用来判断是否加锁
    //int 默认 为0   Thread  默认 null
    AtomicReference<Thread> atomicReference=new AtomicReference<>();

    //加锁  自旋
    public void myLock(){
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName()+"===>mylock");
        //自旋锁
        while (!atomicReference.compareAndSet(null,thread)){

        }
    }

    //解锁不需要自旋
    public void myUnLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + "===>myunlock");
        atomicReference.compareAndSet(thread,null);
    }
}
