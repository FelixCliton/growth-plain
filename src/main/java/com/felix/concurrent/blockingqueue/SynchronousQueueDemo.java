package com.felix.concurrent.blockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/4/13 8:55 PM
 * @desc:
 */
public class SynchronousQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue();

        new Thread(()->{
            try {

                System.out.println(Thread.currentThread().getName()+"\t add 1");
                blockingQueue.put("1");
                System.out.println(Thread.currentThread().getName()+"\t add 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName()+"\t add 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"A").start();


        new Thread(()->{
            try {
                Thread.sleep(5000L);

                String value = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"\t take "+value);
                Thread.sleep(5000L);

                value = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"\t take "+value);
                Thread.sleep(5000L);

                value = blockingQueue.take();
                System.out.println(Thread.currentThread().getName()+"\t take "+value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"B").start();
    }

}
