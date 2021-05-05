package com.felix.concurrent.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/5/5 10:16
 * @desc:
 */
public class AQSDemo {
    public static void main(String[] args) {
       Lock lock = new ReentrantLock();
        //带入一个银行办理业务的案例来模拟AQS如何进行线程的管理和通知唤醒机制
        //3个线程模拟3个来银行网点办理业务的顾客
        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"=========come in ");
                TimeUnit.MINUTES.sleep(20L);
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"A").start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"=========come in ");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"B").start();

        new Thread(()->{
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName()+"=========come in ");
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        },"C").start();
    }

}
