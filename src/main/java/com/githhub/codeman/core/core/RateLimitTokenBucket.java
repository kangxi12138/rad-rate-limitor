

package com.githhub.codeman.core.core;


import com.alibaba.fastjson.JSON;
import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;
import com.githhub.codeman.core.dto.RateLimitTokenBucketDto;
import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.InnerRateLimitUtils;

import com.githhub.codeman.core.util.StringUtil;
import com.githhub.codeman.core.util.timer.ITimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RateLimitTokenBucket extends AbstractRateLimit {

    private static final Logger LOG =  LoggerFactory.getLogger(RateLimitTokenBucket.class);

    
    @Override
    public boolean doAcquire(String cacheKey,
                             RadRateLimitConfig configDto,
                             RadRateLimitContext context) {
        final long rate = InnerRateLimitUtils.calcRate(configDto);
        RateLimitTokenBucketDto rateLimitTokenBucketDto = getRateLimitBucketDto(cacheKey, rate, context);
        final ICommonCacheService commonCacheService = context.cacheService();
        final ITimer timer = context.timer();

        int permits = configDto.getPermits();
        long tokenNum = rateLimitTokenBucketDto.getTokenNum();
        if (tokenNum < permits) {
            // 加入令牌
            long now = timer.time();
            long durationMs = now - rateLimitTokenBucketDto.getLastUpdateTime();
            // 增量部分
            long addTokenNum = (long) (durationMs * 1.0 * rate / 1000);
            LOG.debug("[Limit] add token is " + addTokenNum);

            // 新的令牌数量，丢弃超出的部分
            long newTokenNum = Math.min(addTokenNum + tokenNum, rateLimitTokenBucketDto.getCapacity());
            if (newTokenNum >= permits) {
                rateLimitTokenBucketDto.setLastUpdateTime(now);
                rateLimitTokenBucketDto.setTokenNum(newTokenNum - permits);
                commonCacheService.set(cacheKey, JSON.toJSONString(rateLimitTokenBucketDto));
                return true;
            } else {
                // 时间不够
                return false;
            }
        } else {
            // 正常够使用的场景
            rateLimitTokenBucketDto.setTokenNum(tokenNum - permits);
            commonCacheService.set(cacheKey, JSON.toJSONString(rateLimitTokenBucketDto));
        }

        return true;
    }


    
    private RateLimitTokenBucketDto getRateLimitBucketDto(String cacheKey,
                                                          long rate,
                                                          RadRateLimitContext context) {
        final ICommonCacheService commonCacheService = context.cacheService();
        final ITimer timer = context.timer();

        String dtoJson = commonCacheService.get(cacheKey);
        RateLimitTokenBucketDto bucketDto = null;
        if (StringUtil.isNotEmpty(dtoJson)) {
            bucketDto = JSON.parseObject(dtoJson, RateLimitTokenBucketDto.class);
        } else {
            // 初始化
            bucketDto = new RateLimitTokenBucketDto();
            bucketDto.setRate(rate);
            bucketDto.setCapacity(rate * 8);
            // 默认初始化为 1，应该比较合理一点
            // 如果是0，可能导致一开始无法访问
            bucketDto.setTokenNum(1);
            bucketDto.setLastUpdateTime(timer.time());
        }

        return bucketDto;
    }

}
