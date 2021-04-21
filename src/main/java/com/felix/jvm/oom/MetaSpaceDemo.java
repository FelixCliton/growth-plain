package com.felix.jvm.oom;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author liujie3@yidian-inc.com
 * @version 1.0
 * @date 2021/4/21 23:36
 * @desc:
 */
public class MetaSpaceDemo {
    public static void main(String[] args) {
        int i = 0;
        try{
            while (true){
                i++;
                Enhancer enhancer = new Enhancer();
                enhancer.setSuperclass(OOMTest.class);
                enhancer.setUseCache(false);
                enhancer.setCallback(new MethodInterceptor() {
                    @Override
                    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                        return methodProxy.invokeSuper(o,args);
                    }
                });
                enhancer.create();
            }
        }catch (Exception e){
            System.out.println("******************次后发生了异常："+i);
            e.printStackTrace();
        }
    }

    static class OOMTest{ }
}
