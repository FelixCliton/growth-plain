package com.felix.concurrent.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/4/17 1:18 PM
 * @desc:
 */
public class ThreadPoolDemo {

    public static void main(String[] args) {

//        ExecutorService threadPool = Executors.newFixedThreadPool(5); //一池5线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor(); //一池1线程
        ExecutorService threadPool = Executors.newCachedThreadPool(); //一池多线程

        try {
            for (int i = 0; i < 100; i++) {
                threadPool.execute(()->{
                    System.out.println(Thread.currentThread().getName()+"\t 办理业务");
                    try {
                        TimeUnit.MICROSECONDS.sleep(10L);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                });
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }

    }

}
