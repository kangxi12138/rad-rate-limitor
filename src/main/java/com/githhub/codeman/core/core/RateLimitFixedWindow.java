

package com.githhub.codeman.core.core;


import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;

import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class RateLimitFixedWindow extends AbstractRateLimit {

    
    private static final Logger LOG =  LoggerFactory.getLogger(RateLimitFixedWindow.class);

    
    @Override
    protected boolean doAcquire(String cacheKey, RadRateLimitConfig configDto, RadRateLimitContext rateLimitContext) {
        final ICommonCacheService cacheService = rateLimitContext.cacheService();
        final int permits = configDto.getPermits();

        String cacheValue = cacheService.get(cacheKey);
        if(StringUtil.isEmpty(cacheKey)) {
            final long expireMills = configDto.getTimeUnit().toMillis(configDto.getInterval());
            LOG.info("cacheKey: {} 对应的历史配置为空，进行初始化");
            // 模式初始化为0次
            cacheValue = "0";
            cacheService.set(cacheKey, cacheValue, expireMills);
        }

        long cacheCount = Long.parseLong(cacheValue);

        long newCount = cacheCount + permits;
        final long configCount = configDto.getCount();
        if(newCount > configCount) {
            LOG.warn("newCount {} 大于配置的 {}", newCount, configCount);
            return false;
        } else {
            long ttlMills = cacheService.ttl(cacheKey);
            if(ttlMills > 0) {
                // 直接 set 一个值，redis 会将其有效期设置为永远。
                cacheService.set(cacheKey, cacheValue, ttlMills);
            }

            return true;
        }
    }

}
