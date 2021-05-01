package com.felix.jvm.GC;

import java.util.Random;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/5/1 9:17
 * @desc:
 *
 */
public class CMSGCDemo {

    /**
     * -XX:+PrintCommandLineFlags -XX:+UseConcMarkSweepGC -XX:+PrintGCDetails -Xms10m -Xmx10m
     * @param args
     */
    public static void main(String[] args) {
        String str = "hahhah";
        while (true) {
            str += str + new Random().nextInt(7777777) + new Random().nextInt(999999999);
            str.intern();
        }
    }

}
