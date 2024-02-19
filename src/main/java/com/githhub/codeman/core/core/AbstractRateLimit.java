package com.githhub.codeman.core.core;


import com.githhub.codeman.api.core.RadRateLimit;
import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;
import com.githhub.codeman.api.support.*;

import com.githhub.codeman.core.support.reject.RateLimitRejectListenerContext;

import com.githhub.codeman.core.util.CollectionUtil;
import com.githhub.codeman.core.util.ICondition;
import com.githhub.codeman.core.util.InnerRateLimitUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public abstract class AbstractRateLimit implements RadRateLimit {

    
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRateLimit.class);

    
    protected  abstract boolean doAcquire(String cacheKey,
                                         RadRateLimitConfig configDto,
                                         RadRateLimitContext rateLimitContext);

    @Override
    public boolean tryAcquire(RadRateLimitContext context) {
        //1. 基本信息
        final Method method = context.method();
        final Object[] args = context.args();
        final RadRateLimitTokenService tokenService = context.tokenService();
        final RadRateLimitMethodService methodService = context.methodService();
        final String tokenId = tokenService.getTokenId(args);
        final String methodId = methodService.getMethodId(method, args);
        final String cacheKeyNamespace = context.cacheKeyNamespace();

        //2. 查询配置信息
        final RadRateLimitConfigService configService = context.configService();
        List<RadRateLimitConfig> configDtoList = configService.queryConfigList(tokenId, methodId, method);

        //2.1 只保留启用的配置
        List<RadRateLimitConfig> enableConfigList = CollectionUtil.conditionList(configDtoList, new ICondition<RadRateLimitConfig>() {
            @Override
            public boolean condition(RadRateLimitConfig rateLimitConfigDto) {
                return rateLimitConfigDto.isEnable();
            }
        });

        //3. 最后的结果
        boolean acquireFlag = false;
        if(CollectionUtil.isEmpty(enableConfigList)) {
            LOG.info("method {} 对应的配置为空，不做限制", methodId);
            acquireFlag = true;
        } else {
            acquireFlag = tryAcquire(enableConfigList, methodId, tokenId, context);
        }

        final RadRateLimitRejectListener rejectListener = context.rejectListener();
        final RadRateLimitRejectListenerContext rejectListenerContext = RateLimitRejectListenerContext.newInstance()
                .acquireFlag(acquireFlag)
                .method(method)
                .args(args)
                .rejectListener(rejectListener)
                .tokenService(tokenService)
                .methodService(methodService)
                .configService(configService)
                .cacheService(context.cacheService())
                .configList(enableConfigList)
                .timer(context.timer())
                .cacheKeyNamespace(cacheKeyNamespace);

        rejectListener.listen(rejectListenerContext);

        return acquireFlag;
    }

    protected boolean tryAcquire(List<RadRateLimitConfig> configDtoList,
                                 String methodId,
                                 String tokenId,
                                 final RadRateLimitContext context) {
        // 全部通过则为通过
        final Set<Long> rateSet = new HashSet<>();
        List<Boolean> resultFlagList = new ArrayList<>();

        // 需要全部遍历，不然对应的消耗数据不准
        final String cacheKeyNamespace = context.cacheKeyNamespace();

        for(RadRateLimitConfig configDto : configDtoList) {
            // 速率
            Long rate = InnerRateLimitUtils.calcRate(configDto);
            // 主要是避免令牌被重复消费的问题
            if(rateSet.contains(rate)) {
                LOG.info("配置 {} 对应的速率已存在 {}", configDto, rate);
                continue;
            }
            rateSet.add(rate);

            // 构建 key
            String cacheKey = buildCacheKey(cacheKeyNamespace, tokenId, methodId, rate);
            // 执行结果
            boolean resultFlag = doAcquire(cacheKey, configDto, context);

            resultFlagList.add(resultFlag);
        }

        // 全部通过，才认为是通过
        return !resultFlagList.contains(Boolean.FALSE);
    }

    
    protected String buildCacheKey(String cacheKeyNamespace, String tokenId, String methodId, Long rate) {
        String format = "%s:%s:%s:%s";
        return String.format(format, cacheKeyNamespace, tokenId, methodId, rate);
    }

}
