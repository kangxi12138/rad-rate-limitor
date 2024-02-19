package com.githhub.codeman.core.support.proxy.cglib;


import com.githhub.codeman.core.builder.RadRateLimitBuilder;
import com.githhub.codeman.core.support.proxy.AbstractProxy;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


public class CglibProxy extends AbstractProxy implements MethodInterceptor {

    
    private final Object target;

    public CglibProxy(Object target) {
        this.target = target;
    }

    public CglibProxy(Object target, RadRateLimitBuilder bs) {
        super(bs);
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        //1. 添加判断
        super.rateLimitBs.tryAcquire(method, objects);

        //2. 返回结果
        return method.invoke(target, objects);
    }

    @Override
    public Object proxy() {
        Enhancer enhancer = new Enhancer();
        //目标对象类
        enhancer.setSuperclass(target.getClass());
        enhancer.setCallback(this);
        //通过字节码技术创建目标对象类的子类实例作为代理
        return enhancer.create();
    }

}
