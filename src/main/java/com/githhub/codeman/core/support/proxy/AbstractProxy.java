package com.githhub.codeman.core.support.proxy;


import com.githhub.codeman.core.builder.RadRateLimitBuilder;
import com.githhub.codeman.core.util.IProxy;


public abstract class AbstractProxy implements IProxy {

    
    protected final RadRateLimitBuilder rateLimitBs;

    protected AbstractProxy(RadRateLimitBuilder rateLimitBs) {
        this.rateLimitBs = rateLimitBs;
    }

    public AbstractProxy() {
        this.rateLimitBs = RadRateLimitBuilder.newBuilder();
    }

}
