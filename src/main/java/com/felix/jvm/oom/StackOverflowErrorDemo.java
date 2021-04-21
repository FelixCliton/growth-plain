package com.felix.jvm.oom;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/21 22:34
 * @desc:
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        stackOverflowError();
    }

    private static void stackOverflowError() {
        stackOverflowError();
    }

}
