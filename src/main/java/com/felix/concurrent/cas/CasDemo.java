package com.felix.concurrent.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/10 7:21 PM
 * @desc: CAS是什么？比较并交换
 */
public class CasDemo {

    public static void main(String[] args) {
        AtomicInteger integer = new AtomicInteger(5);

        System.out.println(integer.compareAndSet(5, 2021)+"\tcurrent data:"+integer.get());
        System.out.println(integer.compareAndSet(5, 1024)+"\tcurrent data:"+integer.get());


        integer.getAndIncrement();
    }

}
