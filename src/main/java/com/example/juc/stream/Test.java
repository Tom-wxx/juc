package com.example.juc.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Test {
    public static void main(String[] args) {
        User wxx = new User(1, "wxx", 21);
        User sxx = new User(1, "sxx", 22);
        User axx = new User(1, "axx", 23);
        User dxx = new User(1, "dxx", 24);
        User fxx = new User(1, "fxx", 25);
        List<User> users = Arrays.asList(wxx, sxx, axx, dxx, fxx);  //添加到集合中去
        /**计算交给stream去
         * lambda表达式  链式编程  函数式接口  Stream流式计算*/
        users.stream().filter(s->{ return s.getAge()%2==0;})
                      .filter(s->{return s.getAge()>23;})    //内部是Predicate断定型接口
                      .map(s->{return s.getName().toUpperCase(Locale.ROOT);})  //内部是Function函数式接口
                      .sorted((w,e)->{return w.compareTo(e);})  //倒序排序
                      .limit(1)
                      .forEach(System.out::println);
    }
}
