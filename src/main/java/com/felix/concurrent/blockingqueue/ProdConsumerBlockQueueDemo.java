package com.felix.concurrent.blockingqueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/4/16 11:15 AM
 * @desc: volatile/cas/atomic/原子引用/blockQueue
 */
public class ProdConsumerBlockQueueDemo {


    public static void main(String[] args) throws InterruptedException {

        ShareData shareData = new ShareData(new ArrayBlockingQueue(10));

        new Thread(()->{
            try {
                shareData.prod();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"prod").start();

        new Thread(()->{
            try {
                shareData.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"consume").start();

        Thread.sleep(10000L);
        shareData.stop();
    }


}

class ShareData {

    private volatile boolean FLAG = true;

    private AtomicInteger number = new AtomicInteger();

    private BlockingQueue<Integer> queue = null;

    public ShareData(BlockingQueue queue) {
        this.queue = queue;
    }

    public void prod() throws InterruptedException {
        while (FLAG) {
            Integer data = number.incrementAndGet();

            boolean returnVal = queue.offer(data, 3L, TimeUnit.SECONDS);
            if (returnVal) {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列：" + data + "\t成功");
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 插入队列：" + data + "\t失败");
            }
            Thread.sleep(1000L);
        }
        System.out.println(Thread.currentThread().getName() + "生产者停止生产");
    }

    public void consume() throws InterruptedException {
        while (FLAG) {
            Integer data = queue.poll(3L, TimeUnit.SECONDS);
            if (null == data) {
                FLAG = false;
                System.out.println(Thread.currentThread().getName()+"\t消费数据超时，停止消费");
                return;
            }
            System.out.println(Thread.currentThread().getName() + "\t消费数据：" + data + "\t成功");
        }
    }

    public void stop(){
        FLAG = false;
        System.out.println("主动停止生产消费。");
    }
}
