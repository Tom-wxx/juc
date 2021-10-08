package com.example.juc.rw;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 独占锁（写锁） 一次只能被一个线程占有
 * 共享锁（读锁） 多个线程可以同时占有
 * ReadWriteLock
 * 读-读  可以共存！
 * 读-写  不能共存！
 * 写-写  不能共存！
 */
public class ReadWriteLockTest {
    public static void main(String[] args) {
        MyCacheLock myCacheLock = new MyCacheLock();
        // 写入
        for (int i = 1; i <= 11; i++) {
            final int tem=i;
            new Thread(()->{
                myCacheLock.put(tem+"",tem+"");
            },String.valueOf(i)).start();
        }
        // 读取
        for (int i = 1; i <= 11; i++) {
            final int temp=i;
            new Thread(()->{
                myCacheLock.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}

/**
 * 自定义缓存
 * 加锁的
 */
class MyCacheLock{

    private volatile Map<String,Object> map =new HashMap<>();
    //读写锁： 更加细粒度的控制
    private ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
    //private Lock lock=new ReentrantLock();

    // 存，写入的时候，只希望同时只有一个线程写
    public void put(String key,Object value){
        //lock.lock();
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           //lock.unlock();
            readWriteLock.writeLock().unlock();
        }
    }
    public void get(String key){
        //lock.lock();
        readWriteLock.readLock().lock();

        try {
            System.out.println(Thread.currentThread().getName()+"读取"+key);
            Object o = map.get(key);
            System.out.println(Thread.currentThread().getName()+"读取ok");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //lock.unlock();
            readWriteLock.readLock().unlock();
        }

    }

/**
 * 自定义缓存
 * 不加锁的
 */
class MyCache{
    private volatile Map<String,Object> map=new HashMap<>();

    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"写入ok");
    }

    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读取"+key);
        Object o = map.get(key);
        System.out.println(Thread.currentThread().getName()+"读取ok");
    }

}
}
