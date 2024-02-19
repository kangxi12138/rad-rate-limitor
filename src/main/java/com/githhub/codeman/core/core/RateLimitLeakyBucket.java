

package com.githhub.codeman.core.core;


import com.alibaba.fastjson.JSON;
import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;
import com.githhub.codeman.core.dto.RateLimitLeakyBucketDto;
import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.InnerRateLimitUtils;

import com.githhub.codeman.core.util.StringUtil;
import com.githhub.codeman.core.util.timer.ITimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




public class RateLimitLeakyBucket extends AbstractRateLimit {

    private static final Logger LOG = LoggerFactory.getLogger(RateLimitLeakyBucket.class);

    
    @Override
    public boolean doAcquire(String cacheKey,
                                          RadRateLimitConfig configDto,
                                          RadRateLimitContext context) {
        final long rate = InnerRateLimitUtils.calcRate(configDto);
        RateLimitLeakyBucketDto rateLimitTokenBucketDto = getRateLimitBucketDto(cacheKey, rate, context);

        int permits = configDto.getPermits();
        long water = calcWater(rateLimitTokenBucketDto, context, rate);
        final long capacity = rateLimitTokenBucketDto.getCapacity();
        final long newWater = water + permits;
        if (newWater <= capacity) {
            final ITimer timer = context.timer();

            // 尝试加水,并且水还未满
            rateLimitTokenBucketDto.setWater(newWater);
            rateLimitTokenBucketDto.setLastUpdateTime(timer.time());

            final ICommonCacheService commonCacheService = context.cacheService();
            commonCacheService.set(cacheKey, JSON.toJSONString(rateLimitTokenBucketDto));
            return true;
        } else {
            // 水满，拒绝加水
            LOG.info("[RateLimit] leaky water is has been full!");
            return false;
        }
    }

    
    private long calcWater(RateLimitLeakyBucketDto bucketDto,
                           RadRateLimitContext rateLimitContext,
                           final long rate) {
        long now = rateLimitContext.timer().time();
        long lastUpdateTime = bucketDto.getLastUpdateTime();
        // 先执行漏水，计算剩余水量
        long durationMs = now - lastUpdateTime;
        long leakyWater = (long) (durationMs * 1.0 * rate / 1000);
        LOG.info("[RateLimit] leaky water is " + leakyWater);
        long water = bucketDto.getWater();

        // 确保最小为 0
        return Math.max(0, water - leakyWater);
    }

    
    private RateLimitLeakyBucketDto getRateLimitBucketDto(String cacheKey,
                                                          long rate,
                                                          RadRateLimitContext context) {
        final ICommonCacheService commonCacheService = context.cacheService();
        final ITimer timer = context.timer();

        String dtoJson = commonCacheService.get(cacheKey);
        RateLimitLeakyBucketDto bucketDto = null;
        if(StringUtil.isNotEmpty(dtoJson)) {
            bucketDto = JSON.parseObject(dtoJson, RateLimitLeakyBucketDto.class);
        } else {
            // 初始化
            bucketDto = new RateLimitLeakyBucketDto();
            bucketDto.setRate(rate);
            bucketDto.setCapacity(rate * 8);

            // 水量初始化为 0
            bucketDto.setWater(0);
            bucketDto.setLastUpdateTime(timer.time());
        }

        return bucketDto;
    }


}
