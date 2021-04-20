package com.felix.jvm.reference;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/20 22:45
 * @desc:
 */
public class WeakHashMapDemo {

    public static void main(String[] args) {

        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "value";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }

    public static void myHashMap(){
        Map<Integer,String> map = new HashMap<>();
        Integer key = new Integer(2);
        String value = "value";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }

    public static void myWeakHashMap(){
        WeakHashMap<Integer,String> map = new WeakHashMap<>();
        Integer key = new Integer(1);
        String value = "value";
        map.put(key,value);
        System.out.println(map);
        key = null;
        System.out.println(map);
        System.gc();
        System.out.println(map);
    }
}
