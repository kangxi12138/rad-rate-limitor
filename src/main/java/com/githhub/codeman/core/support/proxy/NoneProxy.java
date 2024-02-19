package com.githhub.codeman.core.support.proxy;


import com.githhub.codeman.core.util.IProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class NoneProxy implements InvocationHandler, IProxy {
    private final Object target;

    public NoneProxy(Object target) {
        this.target = target;
    }

    public Object proxy() {
        return this.target;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxy, args);
    }
}
