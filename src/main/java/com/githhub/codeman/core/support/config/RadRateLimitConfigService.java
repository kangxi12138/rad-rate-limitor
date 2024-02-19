package com.githhub.codeman.core.support.config;


import com.githhub.codeman.api.dto.RadRateLimitConfig;
import com.githhub.codeman.core.annotation.RateLimit;
import com.githhub.codeman.core.annotation.RateLimits;
import com.githhub.codeman.core.util.ArrayUtil;
import com.githhub.codeman.core.util.CollectionUtil;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class RadRateLimitConfigService implements com.githhub.codeman.api.support.RadRateLimitConfigService {

    @Override
    public List<RadRateLimitConfig> queryConfigList(String tokenId, String methodId, Method method) {
        //1. 方法上的优先级较高，如果存在一个，则直接忽略类上的。避免混乱。
        RateLimit methodRateLimit = method.getAnnotation(RateLimit.class);
        RateLimits methodRateLimits = method.getAnnotation(RateLimits.class);
        List<RadRateLimitConfig> methodConfigList = buildConfigList(methodRateLimit, methodRateLimits);
        if(CollectionUtil.isNotEmpty(methodConfigList)) {
            return methodConfigList;
        }

        //2. 类上的信息
        final Class<?> clazz = method.getDeclaringClass();
        RateLimit clazzRateLimit = clazz.getAnnotation(RateLimit.class);
        RateLimits clazzRateLimits = clazz.getAnnotation(RateLimits.class);
        List<RadRateLimitConfig> clazzConfigList = buildConfigList(clazzRateLimit, clazzRateLimits);
        if(CollectionUtil.isNotEmpty(clazzConfigList)) {
            return clazzConfigList;
        }

        //3. 返回空
        return Collections.emptyList();
    }

    private List<RadRateLimitConfig> buildConfigList(RateLimit rateLimit,
                                                     RateLimits rateLimits) {
        List<RadRateLimitConfig> resultList = new ArrayList<>();

        //1. 注解
        RadRateLimitConfig rateLimitConfig = buildConfig(rateLimit);
        if(rateLimitConfig != null) {
            resultList.add(rateLimitConfig);
        }

        //2. 重复注解
        if(rateLimits != null) {
            RateLimit[] rateLimitsArray = rateLimits.value();
            if(ArrayUtil.isNotEmpty(rateLimitsArray)) {
                for(RateLimit limit : rateLimitsArray) {
                    RadRateLimitConfig configDto = buildConfig(limit);
                    if(configDto != null) {
                        resultList.add(configDto);
                    }
                }
            }
        }

        //3. 返回结果
        return resultList;
    }

    
    private RadRateLimitConfig buildConfig(RateLimit rateLimit) {
        if(rateLimit == null) {
            return null;
        }

        RadRateLimitConfig configDto = new RadRateLimitConfig();
        configDto.setCount(rateLimit.count());
        configDto.setInterval(rateLimit.interval());
        configDto.setPermits(rateLimit.value());
        configDto.setTimeUnit(rateLimit.timeUnit());
        configDto.setEnable(rateLimit.enable());

        return configDto;
    }

}
