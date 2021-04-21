package com.felix.jvm.oom;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/21 23:20
 * @desc:
 */
public class UnableCreateNewThreadDemo {

    public static void main(String[] args) {
        for (int i = 0;  ; i++) {
            System.out.println("======================"+i);
            new Thread(()->{
                try {
                    Thread.sleep(Integer.MAX_VALUE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },""+i).start();
        }
    }

}
