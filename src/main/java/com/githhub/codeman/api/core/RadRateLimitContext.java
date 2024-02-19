/*
 * Copyright (c)  2018. houbinbin Inc.
 * rate-tryAcquire All rights reserved.
 */

package com.githhub.codeman.api.core;


import com.githhub.codeman.api.support.RadRateLimitConfigService;
import com.githhub.codeman.api.support.RadRateLimitMethodService;
import com.githhub.codeman.api.support.RadRateLimitRejectListener;
import com.githhub.codeman.api.support.RadRateLimitTokenService;
import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.timer.ITimer;


import java.lang.reflect.Method;


public interface RadRateLimitContext {


    ITimer timer();

    RadRateLimitConfigService configService();


    RadRateLimitMethodService methodService();


    RadRateLimitTokenService tokenService();

    ICommonCacheService cacheService();


    RadRateLimitRejectListener rejectListener();


    Method method();


    Object[] args();


    String cacheKeyNamespace();

}
