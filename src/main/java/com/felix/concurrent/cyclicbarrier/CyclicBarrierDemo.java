package com.felix.concurrent.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/3/28 21:13
 * @desc:
 */
public class CyclicBarrierDemo {

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("----召唤神龙");
        });

        for (int i = 1; i <=7 ; i++) {
            int finalI = i;
            new Thread(()->{
                System.out.println("收集到龙珠："+ finalI);
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }
    }

}
