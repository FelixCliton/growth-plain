package com.felix.concurrent.Volatile;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/5 11:21 AM
 * @desc:
 */
public class VisibleDemo {

    public static void main(String[] args) {

        MyData data = new MyData();

        new Thread(() -> {
            System.out.println("come in thread A...");
            System.out.println("the number:" + data.number);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.setNumber(10);
            System.out.println("thread A over.the number:" + data.number);
        }).start();

        while (data.number == 0) {

        }
        System.out.println("main thread over.the number:" + data.number);
    }
}

class MyData {
    int number = 0;
//    volatile int number = 0;

    public void setNumber(int number) {
        this.number = number;
    }
}