package com.felix.jvm.oom;

import java.nio.ByteBuffer;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/21 23:02
 * @desc:
 */
public class DirectBufferMemoryDemo {

    public static void main(String[] args) {
        System.out.println("配置的maxDirectMemory：" + (sun.misc.VM.maxDirectMemory()) / (double) 1024 / 1024 + "MB");
        try {
            Thread.sleep(3000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer.allocateDirect(6*1024*1024);
    }

}
