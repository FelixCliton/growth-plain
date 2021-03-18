package com.felix.concurrent.container;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;


/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/3/15 22:44
 * @desc: 集合类不安全问题
 */
public class ContainerNotSafeDemo {

    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

}
