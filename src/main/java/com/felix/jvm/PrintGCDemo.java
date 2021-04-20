package com.felix.jvm;

import static java.lang.Integer.MAX_VALUE;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/18 23:20
 * @desc:
 */
public class PrintGCDemo {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("**************helloGC***********");
        Thread.sleep(MAX_VALUE);
    }

}
