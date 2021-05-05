package com.felix.concurrent.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/5/4 14:40
 * @desc:
 */
public class LockSupportDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread a = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t------------come in\t"+System.currentTimeMillis());
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t--------------被唤醒\t"+System.currentTimeMillis());
        }, "A");
        a.start();
        Thread b = new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t------------unpark()");
            LockSupport.unpark(a);
        }, "B");
        b.start();
    }
}
