package com.felix.concurrent.thread;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/4/18 10:57 AM
 * @desc: 死锁是两个或者两个以上线程在执行过程中，因争夺资源而造成的一种相互等待的现象，若无外力干涉那他们都将无法推进下去
 */
public class DeadLockDemo {

    public static void main(String[] args) {

        String lockA = "lockA";
        String lockB = "lockB";

        new Thread(new HoldLockThread(lockA,lockB),"A").start();
        new Thread(new HoldLockThread(lockB,lockA),"B").start();

        //linux  ps -ef |grep xxx
        //jps java版的ps,查看java进程 jps -l
    }

}

class HoldLockThread implements Runnable {

    HoldLockThread(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    private String lockA;

    private String lockB;

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockA + "\t尝试获得：" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "\t自己持有：" + lockB + "\t尝试获得：" + lockA);
                try {
                    Thread.sleep(2000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
