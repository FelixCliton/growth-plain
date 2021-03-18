package com.felix.concurrent.lock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/18 7:31 PM
 * @desc:
 */

class MyCache {

    private volatile Map<String, Object> map = new HashMap<>();
    private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value) {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在写入：" + key);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成：");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.writeLock().unlock();
        }
    }


    public Object get(String key) {

        readWriteLock.readLock().lock();
        Object o = null;
        try {
            System.out.println(Thread.currentThread().getName() + "\t 正在读取：");
            o = map.get(key);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t 读取完成：" + o);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            readWriteLock.readLock().unlock();
        }
        return o;
    }
}

public class ReadWriteLockDemo {


    /**
     * 读读共存
     * 读写、写读、写写不能共存
     *
     * @param args
     */
    public static void main(String[] args) {

        MyCache myCache = new MyCache();

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                myCache.put(finalI + "", finalI + "");
            }, "w" + i).start();
        }

        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                Object o = myCache.get(finalI + "");
            }, "r" + i).start();
        }
    }

}
