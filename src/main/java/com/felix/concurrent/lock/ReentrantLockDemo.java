package com.felix.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/18 3:22 PM
 * @desc:
 */
public class ReentrantLockDemo {
    /**
     * 可重入锁（也叫递归锁）
     *
     * @param args
     */
    public static void main(String[] args) {
        Phone phone = new Phone();
        new Thread(() -> {
            phone.sendSMS();
        }, "t1").start();
        new Thread(() -> {
            phone.sendSMS();
        }, "t2").start();


        Thread t3  =new Thread(phone,"t3");
        Thread t4  =new Thread(phone,"t4");

        t3.start();
        t4.start();
    }
}

class Phone implements Runnable {
    public synchronized void sendSMS() {
        System.out.println(Thread.currentThread().getName() + "\t sendSMS()");
        sendEmail();
    }

    public synchronized void sendEmail() {
        System.out.println(Thread.currentThread().getName() + "\t sendEmail()");
    }

    Lock lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t get()");
            set();
        }finally {
            lock.unlock();
        }
    }

    public void set() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t set()");
        }finally {
            lock.unlock();
        }
    }
}