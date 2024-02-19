

package com.githhub.codeman.core.support.proxy;


import com.githhub.codeman.core.builder.RadRateLimitBuilder;
import com.githhub.codeman.core.support.proxy.cglib.CglibProxy;
import com.githhub.codeman.core.support.proxy.dynamic.DynamicProxy;
import com.githhub.codeman.core.util.ObjectUtil;

import java.lang.reflect.Proxy;


public final class RadRateLimitProxy {

    private RadRateLimitProxy(){}

    
    @SuppressWarnings("all")
    public static <T> T getProxy(final T object) {
        final RadRateLimitBuilder rateLimitBs = RadRateLimitBuilder.newBuilder();

        return getProxy(object, rateLimitBs);
    }

    
    @SuppressWarnings("all")
    public static <T> T getProxy(final T object, final RadRateLimitBuilder rateLimitBs) {
        if(ObjectUtil.isNull(object)) {
            return (T) new NoneProxy(object).proxy();
        }

        final Class clazz = object.getClass();

        // 如果targetClass本身是个接口或者targetClass是JDK Proxy生成的,则使用JDK动态代理。
        // 参考 spring 的 AOP 判断
        if (clazz.isInterface() || Proxy.isProxyClass(clazz)) {
            return (T) new DynamicProxy(object, rateLimitBs).proxy();
        }

        return (T) new CglibProxy(object, rateLimitBs).proxy();
    }

}
