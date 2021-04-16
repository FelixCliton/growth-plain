package com.felix.concurrent.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/14 21:58
 * @desc: //防止虚假唤醒
 */
public class ProdConsumerDemoTraditional {

    public static void main(String[] args) {

        ShardData shardData = new ShardData();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shardData.increment();
            }
        },"A").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shardData.decrement();
            }
        },"B").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shardData.increment();
            }
        },"C").start();

        new Thread(()->{
            for (int i = 0; i <5 ; i++) {
                shardData.decrement();
            }
        },"D").start();
    }

}

class ShardData {

    private int data = 0;

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
//            while (0 != data) {
            if (0 != data) {
                condition.await();
            }
            data++;
            System.out.println(Thread.currentThread().getName() + "\t " + data);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }

    public void decrement() {
        lock.lock();
        try {
//            while (0 == data) {
            if (0 == data) { //if 判断可能存在假唤醒
                condition.await();
            }
            data--;
            System.out.println(Thread.currentThread().getName() + "\t " + data);
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}
