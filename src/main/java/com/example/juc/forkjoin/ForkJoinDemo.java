package com.example.juc.forkjoin;

import java.util.concurrent.RecursiveTask;

public class ForkJoinDemo extends RecursiveTask<Long> {
    private Long start;
    private Long end;

    //临界值
    private Long tem=10000L;

    public ForkJoinDemo(Long start,Long end){
        this.start=start;
        this.end=end;
    }

    //计算方法
    @Override
    protected Long compute() {
        if ((end-start)<tem){
            Long sum=0l;
            for (Long i = start; i <= end; i++) {
                sum+=i;
            }
            return sum;
        }else {
            long middle=(start+end)/2;
            ForkJoinDemo teak1 = new ForkJoinDemo(start,middle);
            teak1.fork();  //拆分任务，把任务压倒线程队列中去
            ForkJoinDemo tesk2 = new ForkJoinDemo(middle+1, end);
            tesk2.fork();//拆分任务，把任务压倒线程队列中去
            //合并任务
            return teak1.join()+tesk2.join();
        }
    }



}
