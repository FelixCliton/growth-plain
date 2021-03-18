package com.felix.concurrent.container;

import sun.misc.Unsafe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/16 9:39 AM
 * @desc:
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {

         Unsafe unsafe = Unsafe.getUnsafe();

//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i <30 ; i++) {

            new Thread(()->{

                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);

            }).start();

        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(list.size());
    }

}
