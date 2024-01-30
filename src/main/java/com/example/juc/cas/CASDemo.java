package com.example.juc.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**CAS  compareAndSet  交换并比较
 *    比较当前工作内存的值与主内存的值，如果这个值是期望的， 那么执行操作   如果不是则循环
 *
 * 缺点： 循环会耗时
 *      一次性只能保证一个共享变量的原子性
 *      产生ABA（狸猫换太子）问题   aba就是 解决就是相当于用 乐观锁解决  引用原子引用
 * */
public class CASDemo {
    public static void main(String[] args) {
      /*  AtomicInteger atomicInteger = new AtomicInteger();
        期望、更新    如我的期望值达到了就更新   否则就不更新    CAS是cpu的原发指令
   public final boolean compareAndSet(int expect, int update)
       atomicInteger.compareAndSet(1,2);*/

        //正常业务业务中比较的是一个个对象
        AtomicStampedReference<Integer> atomicStampedReference = new AtomicStampedReference<Integer>(1,2);
        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();  //可以理解为版本   获得版本号
            System.out.println("a1---》"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(atomicStampedReference.compareAndSet(1, 2, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a2--->"+atomicStampedReference.getStamp());
            System.out.println("第一次："+atomicStampedReference.compareAndSet(2, 1, atomicStampedReference.getStamp(), atomicStampedReference.getStamp() + 1));
            System.out.println("a3--->"+atomicStampedReference.getStamp());

        },"a").start();

        new Thread(()->{
            int stamp = atomicStampedReference.getStamp();  //可以理解为版本   获得版本号
            System.out.println("b1---》"+stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("第二次："+atomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));
            System.out.println("b2--->"+atomicStampedReference.getStamp());
        },"b").start();
    }
}
