package com.example.juc.function;

import java.util.function.Supplier;

/**供给型函数：：   没有参数  只有返回值*/
public class SupplierTest {
    public static void main(String[] args) {
        Supplier<String> objectSupplier = new Supplier<String>() {
            @Override
            public String get() {
                System.out.println("没有返回值");
                return "1024";
            }
        };
        objectSupplier.get();

        Supplier<String> supplier=()->{
            return "2048";
        };
        supplier.get();
    }
}
