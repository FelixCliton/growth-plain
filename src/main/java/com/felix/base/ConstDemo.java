package com.felix.base;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/5/3 19:55
 * @desc:
 */
public class ConstDemo {

    public static void main(String[] args) {

        String str1 = new StringBuilder("ali").append("baba").toString();
        System.out.println(str1);
        System.out.println(str1.intern());
        System.out.println(str1==str1.intern());

        System.out.println();
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2);
        System.out.println(str2.intern());
        System.out.println(str2==str2.intern());
    }

}
