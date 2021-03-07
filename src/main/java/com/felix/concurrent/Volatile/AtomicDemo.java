package com.felix.concurrent.Volatile;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/5 11:21 AM
 * @desc: 验证volatile不保证原子性
 */
public class AtomicDemo {

    public static void main(String[] args) {

        MyData1 data = new MyData1();
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    data.numberIncrease();
                }
            }).start();
        }
        while (Thread.activeCount()>2){
            Thread.yield();
        }
        System.out.println("main thread over.the number:" + data.number);
    }
}

 class MyData1 {
    volatile int number = 0;

    public void numberIncrease() {
        number++;
    }
}