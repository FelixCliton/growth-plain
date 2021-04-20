package com.felix.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/20 22:22
 * @desc:
 */
public class SoftReferenceDemo {

    public static void main(String[] args) {

//        softRefMemoryEnough();

        softRefMemoryNotEnough();

    }

    public static void softRefMemoryNotEnough(){
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o);
        System.out.println(o);
        System.out.println(softReference.get());

        o=null;
        try{
            byte[] bytes = new byte[100*1024*1024];
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(o);
            System.out.println(softReference.get());
        }
    }

    public static void softRefMemoryEnough(){
        Object o = new Object();
        SoftReference<Object> softReference = new SoftReference<Object>(o);
        System.out.println(o);
        System.out.println(softReference.get());

        o=null;
        System.gc();

        System.out.println(o);
        System.out.println(softReference.get());
    }
}
