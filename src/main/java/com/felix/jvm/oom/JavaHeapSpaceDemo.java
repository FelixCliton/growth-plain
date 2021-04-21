package com.felix.jvm.oom;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/21 22:39
 * @desc:
 */
public class JavaHeapSpaceDemo {

    public static void main(String[] args) {
        String str = "hahahhahah";
        while (true){
            str+=str;
            str.intern();
        }
    }

}
