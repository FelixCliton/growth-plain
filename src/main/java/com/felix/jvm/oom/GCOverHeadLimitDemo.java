package com.felix.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/21 22:54
 * @desc:
 */
public class GCOverHeadLimitDemo {

    public static void main(String[] args) {
        Integer i = 0;
        List<String> list = new ArrayList<>();
        while (i<Integer.MAX_VALUE){
            list.add(new String(i+""));
        }
    }
}
