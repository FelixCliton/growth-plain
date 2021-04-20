package com.felix.jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/20 22:22
 * @desc:
 */
public class ReferenceQueueDemo {

    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<Object>(o,referenceQueue);
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
        System.out.println("======================");
        o = null;
        System.gc();
        Thread.sleep(500);
        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(referenceQueue.poll());
    }

}
