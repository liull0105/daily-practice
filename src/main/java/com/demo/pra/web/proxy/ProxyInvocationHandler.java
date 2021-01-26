package com.demo.pra.web.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 自定义动态代理调用处理器
 *
 * @date: 2021/1/26 15:12
 */
public class ProxyInvocationHandler implements InvocationHandler {

    private Object target;

    ProxyInvocationHandler(Object target) {
        this.target = target;
    }

    /**
     * 重写invoke方法
     *
     * @date: 2021/1/26 15:30
     */
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        System.out.println("进入代理调用处理器，调用invoke方法");
        return method.invoke(target, objects);
    }
}
