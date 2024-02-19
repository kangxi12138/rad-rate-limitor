package com.githhub.codeman.core.support.reject;


import com.githhub.codeman.api.dto.RadRateLimitConfig;
import com.githhub.codeman.api.support.*;
import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.timer.ITimer;


import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;


public class RateLimitRejectListenerContext implements com.githhub.codeman.api.support.RadRateLimitRejectListenerContext {

    public static RateLimitRejectListenerContext newInstance() {
        return new RateLimitRejectListenerContext();
    }

    
    private ITimer timer;

    
    private RadRateLimitConfigService configService;

    
    private RadRateLimitMethodService methodService;

    
    private RadRateLimitTokenService tokenService;

    
    private ICommonCacheService cacheService;

    
    private RadRateLimitRejectListener rejectListener;

    
    private Method method;

    
    private Object[] args;

    private String tokenId;

    private String methodId;

    private List<RadRateLimitConfig> configList;

    private boolean acquireFlag;

    
    private String cacheKeyNamespace;

    @Override
    public ITimer timer() {
        return timer;
    }

    public RateLimitRejectListenerContext timer(ITimer timer) {
        this.timer = timer;
        return this;
    }

    @Override
    public RadRateLimitConfigService configService() {
        return configService;
    }

    public RateLimitRejectListenerContext configService(RadRateLimitConfigService configService) {
        this.configService = configService;
        return this;
    }

    @Override
    public RadRateLimitMethodService methodService() {
        return methodService;
    }

    public RateLimitRejectListenerContext methodService(RadRateLimitMethodService methodService) {
        this.methodService = methodService;
        return this;
    }

    @Override
    public RadRateLimitTokenService tokenService() {
        return tokenService;
    }

    public RateLimitRejectListenerContext tokenService(RadRateLimitTokenService tokenService) {
        this.tokenService = tokenService;
        return this;
    }

    @Override
    public ICommonCacheService cacheService() {
        return cacheService;
    }

    public RateLimitRejectListenerContext cacheService(ICommonCacheService cacheService) {
        this.cacheService = cacheService;
        return this;
    }

    @Override
    public RadRateLimitRejectListener rejectListener() {
        return rejectListener;
    }

    public RateLimitRejectListenerContext rejectListener(RadRateLimitRejectListener rejectListener) {
        this.rejectListener = rejectListener;
        return this;
    }

    @Override
    public Method method() {
        return method;
    }

    public RateLimitRejectListenerContext method(Method method) {
        this.method = method;
        return this;
    }

    @Override
    public Object[] args() {
        return args;
    }

    public RateLimitRejectListenerContext args(Object[] args) {
        this.args = args;
        return this;
    }

    @Override
    public String tokenId() {
        return tokenId;
    }

    public RateLimitRejectListenerContext tokenId(String tokenId) {
        this.tokenId = tokenId;
        return this;
    }

    @Override
    public String methodId() {
        return methodId;
    }

    public RateLimitRejectListenerContext methodId(String methodId) {
        this.methodId = methodId;
        return this;
    }

    @Override
    public List<RadRateLimitConfig> configList() {
        return configList;
    }

    public RateLimitRejectListenerContext configList(List<RadRateLimitConfig> configList) {
        this.configList = configList;
        return this;
    }

    @Override
    public boolean acquireFlag() {
        return acquireFlag;
    }

    public RateLimitRejectListenerContext acquireFlag(boolean acquireFlag) {
        this.acquireFlag = acquireFlag;
        return this;
    }

    @Override
    public String cacheKeyNamespace() {
        return cacheKeyNamespace;
    }

    public RateLimitRejectListenerContext cacheKeyNamespace(String cacheKeyNamespace) {
        this.cacheKeyNamespace = cacheKeyNamespace;
        return this;
    }

    @Override
    public String toString() {
        return "RateLimitRejectListenerContext{" +
                "timer=" + timer +
                ", configService=" + configService +
                ", methodService=" + methodService +
                ", tokenService=" + tokenService +
                ", cacheService=" + cacheService +
                ", rejectListener=" + rejectListener +
                ", method=" + method +
                ", args=" + Arrays.toString(args) +
                ", tokenId='" + tokenId + '\'' +
                ", methodId='" + methodId + '\'' +
                ", configList=" + configList +
                ", acquireFlag=" + acquireFlag +
                ", cacheKeyNamespace='" + cacheKeyNamespace + '\'' +
                '}';
    }

}
