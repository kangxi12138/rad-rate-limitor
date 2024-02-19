

package com.githhub.codeman.core.core;


import com.alibaba.fastjson.JSON;
import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;
import com.githhub.codeman.core.dto.RateLimitSlideWindowDto;
import com.githhub.codeman.core.dto.RateLimitSlideWindowInfo;

import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.StringUtil;
import com.githhub.codeman.core.util.timer.ITimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;



public class RateLimitSlideWindow extends AbstractRateLimit {

    private static final Logger LOG = LoggerFactory.getLogger(RateLimitSlideWindow.class);

    
    private final int windowNum;

    public RateLimitSlideWindow(final int windowNum) {
        this.windowNum = windowNum;
    }

    public RateLimitSlideWindow() {
        this(10);
    }

    @Override
    protected boolean doAcquire(String cacheKey, RadRateLimitConfig configDto, RadRateLimitContext rateLimitContext) {
        RateLimitSlideWindowInfo slideWindowInfo = queryCacheInfo(cacheKey, configDto, rateLimitContext);

        //计算总数
        long oldSum = calcSum(slideWindowInfo, rateLimitContext);
        final int permits = configDto.getPermits();
        final long limitCount = configDto.getCount();
        long countVal = oldSum + permits;
        if(countVal > limitCount) {
            LOG.warn("[RateLimit] countVal {} is gt than limit {}", countVal, limitCount);
            return false;
        } else {
            // 计算当前的下标
            long initTime = slideWindowInfo.getInitTime();
            long now = rateLimitContext.timer().time();
            long timeWindow = calcTimeWindow(configDto);

            // 根据时间差，计算当前应该在第一个时间窗口上
            int index = (int) (((now - initTime) / timeWindow) % windowNum);

            List<RateLimitSlideWindowDto> windowDtoList = slideWindowInfo.getWindowList();
            RateLimitSlideWindowDto windowDto = windowDtoList.get(index);
            long oldExpireTime = windowDto.getExpireTime();
            // 过期，则清零。
            if(now > oldExpireTime) {
                RateLimitSlideWindowDto newWindowDto = new RateLimitSlideWindowDto();
                // 过期时间为当前时间的基础上+一个时间窗口
                newWindowDto.setExpireTime(now + timeWindow);
                newWindowDto.setCount(permits);
                windowDtoList.set(index, windowDto);
            } else {
                // 没过期，则累加
                int newCount = windowDto.getCount()+permits;
                windowDto.setCount(newCount);
                windowDtoList.set(index, windowDto);
            }

            // 更新到缓存中
            final ICommonCacheService commonCacheService = rateLimitContext.cacheService();
            commonCacheService.set(cacheKey, JSON.toJSONString(slideWindowInfo));

            return true;
        }
    }

    
    private long calcSum(RateLimitSlideWindowInfo slideWindowInfo,
                         RadRateLimitContext rateLimitContext) {
        long sum = 0;
        long now = rateLimitContext.timer().time();

        List<RateLimitSlideWindowDto> windowList = slideWindowInfo.getWindowList();
        for(RateLimitSlideWindowDto windowDto : windowList) {
            if(windowDto == null) {
                continue;
            }

            long expireTime = windowDto.getExpireTime();
            if(now <= expireTime) {
                long count = windowDto.getCount();
                sum += count;
            }
        }

        return sum;
    }


    
    private RateLimitSlideWindowInfo queryCacheInfo(String cacheKey, RadRateLimitConfig configDto, RadRateLimitContext rateLimitContext) {
        final ICommonCacheService cacheService = rateLimitContext.cacheService();
        String cacheValue = cacheService.get(cacheKey);
        if(StringUtil.isNotEmpty(cacheValue)) {
            // 反序列化
            return JSON.parseObject(cacheValue, RateLimitSlideWindowInfo.class);
        }

        //1. 获取对应的缓存信息
        long timeWindow = calcTimeWindow(configDto);

        final ITimer timer = rateLimitContext.timer();
        final long now = timer.time();
        List<RateLimitSlideWindowDto> windowList = new ArrayList<>(windowNum);
        for(int i = 0; i < windowNum; i++) {
            RateLimitSlideWindowDto windowDto = new RateLimitSlideWindowDto();
            windowDto.setCount(0);
            // 初始化的过期时间不同
            windowDto.setExpireTime(now + i*timeWindow);
            windowList.add(windowDto);
        }

        RateLimitSlideWindowInfo windowInfo = new RateLimitSlideWindowInfo();
        windowInfo.setInitTime(now);
        windowInfo.setWindowList(windowList);
        return windowInfo;
    }

    
    private long calcTimeWindow(RadRateLimitConfig configDto) {
        //1. 获取对应的缓存信息
        long intervalMills = configDto.getTimeUnit().toMillis(configDto.getInterval());
        // 每一个 key 存活的有效期
        return intervalMills / windowNum;
    }

}
