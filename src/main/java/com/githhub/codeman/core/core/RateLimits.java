package com.githhub.codeman.core.core;


import com.githhub.codeman.api.core.RadRateLimit;


public final class RateLimits {

    private RateLimits(){}

    
    public static RadRateLimit fixedWindow() {
        return new RateLimitFixedWindow();
    }

    
    public static RadRateLimit slideWindow() {
        return new RateLimitSlideWindow();
    }

    
    public static RadRateLimit slideWindow(int windowNum) {
        return new RateLimitSlideWindow(windowNum);
    }

    
    public static RadRateLimit slideWindowQueue() {
        return new RateLimitSlideWindowQueue();
    }

    
    public static RadRateLimit leakyBucket() {
        return new RateLimitLeakyBucket();
    }

    
    public static RadRateLimit tokenBucket() {
        return new RateLimitTokenBucket();
    }

}
