package com.felix.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/5/4 11:36
 * @desc: 可重入锁：可以重复递归调用的锁，在外层使用锁之后，在内层仍然可用，并不会发生死锁，这样的锁就叫可重入锁。
 * 在一个synchronized修饰的方法或代码块的内部调用本类的其他synchronized修饰的方法或者代码块时是永远可以得到锁的
 */
public class ReEnterLockDemo {

    private static Object objectLockA = new Object();

    public static void m1() {
        new Thread(() -> {
            synchronized (objectLockA) {
                System.out.println(Thread.currentThread().getName() + "\t ============外层调用");
                synchronized (objectLockA) {
                    System.out.println(Thread.currentThread().getName() + "\t ============中层调用");
                    synchronized (objectLockA) {
                        System.out.println(Thread.currentThread().getName() + "\t ============内层调用");
                    }
                }
            }
        }, "t1").start();
    }


    private static Lock lock = new ReentrantLock();

    public synchronized static void m2() {
        System.out.println(Thread.currentThread().getName() + "\t ============外层调用");
        m3();
    }

    public synchronized static void m3() {
        System.out.println(Thread.currentThread().getName() + "\t ============中层调用");
        m4();
    }

    public synchronized static void m4() {
        System.out.println(Thread.currentThread().getName() + "\t ============内层调用");
    }


    public static void m5() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 外层调用");
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t 内层调用");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void m6() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 外层调用");
            m7();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void m7() {
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 内层调用");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
//        m1();
//        m2();
//        m5();
        m6();
    }


}
