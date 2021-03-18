package com.felix.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/18 4:03 PM
 * @desc:
 */
public class SpinLockDemo {

    public static void main(String[] args) {

        SpinLock spinLock = new SpinLock();

        new Thread(() -> {
            spinLock.lock();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
            }
        }, "t1").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            spinLock.lock();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            spinLock.unlock();

        }, "t2").start();
    }

}

class SpinLock {

    AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        Thread thread = Thread.currentThread();
        System.out.println(thread.getName() + "\t try get lock\t" + System.currentTimeMillis());
        while (!atomicReference.compareAndSet(null, thread)) {

        }
        System.out.println(thread.getName() + "\t get lock\t" + System.currentTimeMillis());
    }

    public void unlock() {
        Thread thread = Thread.currentThread();
        atomicReference.compareAndSet(thread, null);
        System.out.println(thread.getName() + "\t release lock\t" + System.currentTimeMillis());
    }
}
