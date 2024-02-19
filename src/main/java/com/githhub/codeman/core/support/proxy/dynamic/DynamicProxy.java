

package com.githhub.codeman.core.support.proxy.dynamic;


import com.githhub.codeman.core.builder.RadRateLimitBuilder;
import com.githhub.codeman.core.support.proxy.AbstractProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.CompletionService;


public class DynamicProxy extends AbstractProxy implements InvocationHandler {

    
    private final Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    public DynamicProxy(Object target, RadRateLimitBuilder bs) {
        super(bs);
        this.target = target;
    }

    
    @Override
    @SuppressWarnings("all")
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //1. 添加判断
        super.rateLimitBs.tryAcquire(method, args);

        //2. 返回以前的结果
        return method.invoke(target, args);
    }

    @Override
    public Object proxy() {
        // 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new DynamicProxy(target);

        return Proxy.newProxyInstance(handler.getClass().getClassLoader(),
                target.getClass().getInterfaces(), handler);
    }
}
