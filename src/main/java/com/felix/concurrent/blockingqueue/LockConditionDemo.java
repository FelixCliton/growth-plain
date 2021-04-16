package com.felix.concurrent.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/4/16 3:06 PM
 * @desc: //lock condition精确控制和唤醒
 */
public class LockConditionDemo {

//三个线程A，B，C; A操作完B操作，B操作完C操作，C操作完A操作

    public static void main(String[] args) {

        ShardResource shardResource = new ShardResource();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shardResource.op1();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shardResource.op2();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shardResource.op3();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "C").start();


    }
}

class ShardResource {

    private Integer data = 1;
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void op1() {
        lock.lock();
        try {
            while (data != 1) {
                condition1.await();
            }
            data = 2;
            System.out.println(Thread.currentThread().getName() + "\t" + data);
            condition2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void op2() {
        lock.lock();
        try {
            while (data != 2) {
                condition2.await();
            }
            data = 3;
            System.out.println(Thread.currentThread().getName() + "\t" + data);
            condition3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void op3() {
        lock.lock();
        try {
            while (data != 3) {
                condition3.await();
            }
            data = 1;
            System.out.println(Thread.currentThread().getName() + "\t" + data);
            condition1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}