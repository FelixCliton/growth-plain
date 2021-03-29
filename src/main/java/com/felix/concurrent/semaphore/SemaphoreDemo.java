package com.felix.concurrent.semaphore;

import java.util.concurrent.Semaphore;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/3/29 22:58
 * @desc:
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        Semaphore semaphore  =new Semaphore(3);//模拟三个停车位

        for (int i = 1; i <=6 ; i++) {
            //模拟六辆汽车争抢车位
            new Thread(()->{
                try {
                    semaphore.acquire();
                    System.out.println("车辆"+Thread.currentThread().getName()+"驶入停车位~");
                    Thread.sleep(3000L);
                    System.out.println("车辆"+Thread.currentThread().getName()+"驶离停车位~");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }

}
