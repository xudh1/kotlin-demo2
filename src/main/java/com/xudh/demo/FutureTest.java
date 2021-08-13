package com.xudh.demo;

import java.util.concurrent.*;

/**
 * @ClassName : FutureTest
 * @Description :
 * @Author : xudh
 * @Date: 2021-07-07 14:35
 */
public class FutureTest {
    private static ExecutorService pool = Executors.newFixedThreadPool(1);//1

    public static void main(String[] args){
        futureRunnable();
//        futureCallable();
    }
    public static void futureRunnable(){
        Future<?> future = pool.submit(new Runnable(){ //2
            @Override
            public void run() {
                String str = "future runnable!";
                System.out.println(str);
            }
        });
        pool.shutdown();
        try {
            System.out.println("future get = " + future.get());//3
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
//    public static void futureCallable(){
//        Future<?> future = pool.submit(new Callable<String>(){
//            @Override
//            public String call() throws Exception {
//                String str = "future callable!";
//                System.out.println(str);
//                return str;
//            }
//        });
//        pool.shutdown();
//        try {
//            System.out.println("future get = " + future.get());
//        } catch (InterruptedException | ExecutionException e) {
//            e.printStackTrace();
//        }
//    }
}
