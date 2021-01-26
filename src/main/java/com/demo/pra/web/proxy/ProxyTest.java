package com.demo.pra.web.proxy;

import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {

        Subject subject = new SubjectImpl();
        Subject proxy = (Subject) Proxy.newProxyInstance(
                subject.getClass().getClassLoader(),
                subject.getClass().getInterfaces(),
                new ProxyInvocationHandler(subject)
        );
        proxy.sayHello();
    }
}
