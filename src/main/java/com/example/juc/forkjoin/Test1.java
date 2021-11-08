package com.example.juc.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.stream.LongStream;

public class Test1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
           // test1(); //8086
            //test2(); //7868
        test3();   //221
    }

    public static void test1(){
        Long sum=0l;
        long start=System.currentTimeMillis();
        for (Long i =0L; i <= 10_0000_0000; i++) {
            sum+=i;
        }
        long end=System.currentTimeMillis();
        System.out.println("sum:  "+sum+"时间： "+(end-start));
    }
    public static void test2() throws ExecutionException, InterruptedException {
        long start=System.currentTimeMillis();

        ForkJoinDemo test = new ForkJoinDemo(0L,10_0000_0000L);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(test);  //执行任务  没有结果
        ForkJoinTask<Long> submit = forkJoinPool.submit(test);//提交任务  有结果
        Long sum = submit.get();

        long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"时间： "+(end-start));
    }
    public static void test3(){
        long start=System.currentTimeMillis();

        //LongStream 并行流 () (]                                   并行的意思    输出结果
        Long sum = LongStream.rangeClosed(0l, 10_0000_0000l).parallel().reduce(0,Long::sum);

        long end=System.currentTimeMillis();
        System.out.println("sum="+sum+"时间： "+(end-start));
    }
}
