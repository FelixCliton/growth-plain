package com.felix.concurrent.blockingqueue;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/4/16 2:51 PM
 * @desc:
 */
public class ProdConsumerTraditionalDemo {

    public static void main(String[] args) {
        MyShareData shareData = new MyShareData();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.increment();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"prod").start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shareData.decrement();
                try {
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"consumer").start();
    }
}

class MyShareData {

    private Integer data = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void increment() {
        lock.lock();
        try {
            while (data != 0) {
                condition.await();
            }
            data++;
            System.out.println(Thread.currentThread().getName()+"\t 生产数据："+data);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        lock.lock();
        try {
            while (data == 0) {
                condition.await();
            }
            data--;
            System.out.println(Thread.currentThread().getName()+"\t 消费数据："+data);
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

}