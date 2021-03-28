package com.felix.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/3/28 20:22
 * @desc:
 */
public class CountDownLatchDemo {


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch count = new CountDownLatch(10);
        for (int i = 1; i <=10 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println("员工："+ finalI+"下班离开公司。。。。");
                count.countDown();
            },String.valueOf(i)).start();
        }
        count.await();
        System.out.println("所有员工离开公司，保安锁门下班");

    }

}
