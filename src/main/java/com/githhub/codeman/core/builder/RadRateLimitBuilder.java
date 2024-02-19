package com.githhub.codeman.core.builder;


import com.githhub.codeman.api.core.RadRateLimit;
import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.support.RadRateLimitRejectListener;
import com.githhub.codeman.core.constant.RadRateLimitConstants;
import com.githhub.codeman.core.core.RateLimitContext;
import com.githhub.codeman.core.core.RateLimits;
import com.githhub.codeman.core.support.config.RadRateLimitConfigService;
import com.githhub.codeman.core.support.method.RadRateLimitMethodService;

import com.githhub.codeman.core.support.reject.RateLimitRejectListenerException;
import com.githhub.codeman.core.support.token.RadRateLimitTokenService;
import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.cache.CommonCacheServiceMap;
import com.githhub.codeman.core.util.timer.ITimer;
import com.githhub.codeman.core.util.timer.Timers;


import java.lang.reflect.Method;


public final class RadRateLimitBuilder {

    private RadRateLimitBuilder(){}

    
    public static RadRateLimitBuilder newBuilder() {
        return new RadRateLimitBuilder();
    }

    
    private RadRateLimit rateLimit = RateLimits.tokenBucket();

    
    private ITimer timer = Timers.system();

    
    private ICommonCacheService cacheService = new CommonCacheServiceMap();

    
    private com.githhub.codeman.api.support.RadRateLimitConfigService configService = new RadRateLimitConfigService();

    
    private com.githhub.codeman.api.support.RadRateLimitTokenService tokenService = new RadRateLimitTokenService();

    
    private com.githhub.codeman.api.support.RadRateLimitMethodService methodService = new RadRateLimitMethodService();

    
    private RadRateLimitRejectListener rejectListener = new RateLimitRejectListenerException();

    
    private String cacheKeyNamespace = RadRateLimitConstants.DEFAULT_CACHE_KEY_NAMESPACE;

    public RadRateLimitBuilder rateLimit(RadRateLimit rateLimit) {

        this.rateLimit = rateLimit;
        return this;
    }

    public RadRateLimitBuilder timer(ITimer timer) {


        this.timer = timer;
        return this;
    }

    public RadRateLimitBuilder cacheService(ICommonCacheService cacheService) {


        this.cacheService = cacheService;
        return this;
    }

    public RadRateLimitBuilder configService(com.githhub.codeman.api.support.RadRateLimitConfigService configService) {


        this.configService = configService;
        return this;
    }

    public RadRateLimitBuilder tokenService(com.githhub.codeman.api.support.RadRateLimitTokenService tokenService) {

        this.tokenService = tokenService;
        return this;
    }

    public RadRateLimitBuilder methodService(com.githhub.codeman.api.support.RadRateLimitMethodService methodService) {


        this.methodService = methodService;
        return this;
    }

    public RadRateLimitBuilder rejectListener(RadRateLimitRejectListener rejectListener) {


        this.rejectListener = rejectListener;
        return this;
    }

    public RadRateLimitBuilder cacheKeyNamespace(String cacheKeyNamespace) {


        this.cacheKeyNamespace = cacheKeyNamespace;
        return this;
    }

    
    public boolean tryAcquire(Method method,
                              Object[] args) {


        RadRateLimitContext rateLimitContext = RateLimitContext.newInstance()
                .method(method)
                .args(args)
                .timer(timer)
                .configService(configService)
                .tokenService(tokenService)
                .methodService(methodService)
                .rejectListener(rejectListener)
                .cacheService(cacheService)
                .cacheKeyNamespace(cacheKeyNamespace);

        return rateLimit.tryAcquire(rateLimitContext);
    }

}
