package com.felix.jvm.reference;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/20 22:22
 * @desc:
 */
public class WeakReferenceDemo {

    public static void main(String[] args) {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<Object>(o);
        System.out.println(o);
        System.out.println(weakReference.get());

        o = null;

        System.gc();
        System.out.println("======================");
        System.out.println(o);
        System.out.println(weakReference.get());
    }
}
