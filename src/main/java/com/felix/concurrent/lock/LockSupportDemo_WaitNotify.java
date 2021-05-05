package com.felix.concurrent.lock;

import java.util.concurrent.TimeUnit;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/5/4 14:40
 * @desc:
 */
public class LockSupportDemo_WaitNotify {
    private static Object objectLock = new Object();
    public static void main(String[] args) {
        new Thread(() -> {
//            try {
//                Thread.sleep(2000L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            synchronized (objectLock) {
                System.out.println(Thread.currentThread().getName() + "\t------------come in");
                try {
                    objectLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"\t--------------被唤醒");
            }
        }, "A").start();
        new Thread(() -> {
            synchronized (objectLock) {
                objectLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t------------notify");
            }
        }, "B").start();
    }
}
