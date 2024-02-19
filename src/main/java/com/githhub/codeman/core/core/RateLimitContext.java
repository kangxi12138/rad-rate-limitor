package com.githhub.codeman.core.core;


import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.support.RadRateLimitConfigService;
import com.githhub.codeman.api.support.RadRateLimitMethodService;
import com.githhub.codeman.api.support.RadRateLimitRejectListener;
import com.githhub.codeman.api.support.RadRateLimitTokenService;
import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.timer.ITimer;


import java.lang.reflect.Method;


public class RateLimitContext implements RadRateLimitContext {

    
    private ITimer timer;

    
    private RadRateLimitConfigService configService;

    
    private RadRateLimitMethodService methodService;

    
    private RadRateLimitTokenService tokenService;

    
    private ICommonCacheService cacheService;

    
    private RadRateLimitRejectListener rejectListener;

    
    private Method method;

    
    private Object[] args;

    
    private String cacheKeyNamespace;

    public static RateLimitContext newInstance() {
        return new RateLimitContext();
    }

    @Override
    public ITimer timer() {
        return timer;
    }

    public RateLimitContext timer(ITimer timer) {
        this.timer = timer;
        return this;
    }

    @Override
    public RadRateLimitConfigService configService() {
        return configService;
    }

    public RateLimitContext configService(RadRateLimitConfigService configService) {
        this.configService = configService;
        return this;
    }

    @Override
    public RadRateLimitMethodService methodService() {
        return methodService;
    }

    public RateLimitContext methodService(RadRateLimitMethodService methodService) {
        this.methodService = methodService;
        return this;
    }

    @Override
    public RadRateLimitTokenService tokenService() {
        return tokenService;
    }

    public RateLimitContext tokenService(RadRateLimitTokenService tokenService) {
        this.tokenService = tokenService;
        return this;
    }


    @Override
    public RadRateLimitRejectListener rejectListener() {
        return rejectListener;
    }

    public RateLimitContext rejectListener(RadRateLimitRejectListener rejectListener) {
        this.rejectListener = rejectListener;
        return this;
    }

    @Override
    public Method method() {
        return method;
    }

    public RateLimitContext method(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public Object[] args() {
        return args;
    }

    public RateLimitContext args(Object[] args) {
        this.args = args;
        return this;
    }

    @Override
    public ICommonCacheService cacheService() {
        return cacheService;
    }

    public RateLimitContext cacheService(ICommonCacheService cacheService) {
        this.cacheService = cacheService;
        return this;
    }

    @Override
    public String cacheKeyNamespace() {
        return cacheKeyNamespace;
    }

    public RateLimitContext cacheKeyNamespace(String cacheKeyNamespace) {
        this.cacheKeyNamespace = cacheKeyNamespace;
        return this;
    }

}
