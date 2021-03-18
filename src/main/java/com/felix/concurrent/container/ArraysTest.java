package com.felix.concurrent.container;

import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date: 2021/3/18 10:45 AM
 * @desc:
 */
public class ArraysTest {


    public static void main(String[] args) {

        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        System.out.println(list);
        Object[] copyOfList = Arrays.copyOf(list.toArray(), list.size());
        copyOfList[0] = "aa";
        for (Object o : copyOfList) {
            System.out.print(o + "\t ");
        }
        System.out.println();
        System.out.println(list);
    }
}
