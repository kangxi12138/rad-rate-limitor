

package com.githhub.codeman.core.core;


import com.alibaba.fastjson.JSON;
import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;

import com.githhub.codeman.core.util.ICommonCacheService;
import com.githhub.codeman.core.util.StringUtil;
import com.githhub.codeman.core.util.timer.ITimer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;



public class RateLimitSlideWindowQueue extends AbstractRateLimit {

    private static final Logger LOG =  LoggerFactory.getLogger(RateLimitSlideWindowQueue.class);

    @Override
    protected boolean doAcquire(String cacheKey, RadRateLimitConfig configDto, RadRateLimitContext context) {
        // 队列？
        Queue<Long> queue = queryQueue(cacheKey, configDto, context);

        //1. 将时间放入队列中 如果放得下，直接可以执行。反之，需要等待
        //2. 等待完成之后，将第一个元素剔除。将最新的时间加入队列中。
        final ICommonCacheService cacheService = context.cacheService();
        final ITimer timer = context.timer();
        long now = timer.time();

        //2.1 直接放入成功
        boolean offerResult = queue.offer(now);
        if (offerResult) {
            String cacheValue = JSON.toJSONString(queue);
            cacheService.set(cacheKey, cacheValue);
            return true;
        }

        //2.2 直接放入失败
        //2.2.1 取出头节点，获取最初的时间
        long intervalInMills = configDto.getTimeUnit().toMillis(configDto.getInterval());
        Long headTimeInMills = queue.peek();
        long durationMills = now - headTimeInMills;
        if (durationMills > intervalInMills) {
            Long headTimeRemove = queue.poll();
            queue.offer(now);

            LOG.info("Remove head value: {}, add new value: {}",
                    headTimeRemove, now);

            String cacheValue = JSON.toJSONString(queue);
            cacheService.set(cacheKey, cacheValue);
            return true;
        }

        return false;
    }

    private Queue<Long> queryQueue(String cacheKey,
                                   RadRateLimitConfig configDto,
                                   RadRateLimitContext context) {
        final ICommonCacheService cacheService = context.cacheService();
        String cacheValue = cacheService.get(cacheKey);

        int count = configDto.getCount().intValue();
        Queue<Long> queue = new ArrayBlockingQueue<>(count);

        if (StringUtil.isNotEmpty(cacheValue)) {
            List<Long> list = JSON.parseArray(cacheValue, Long.class);
            queue.addAll(list);
            return queue;
        } else {
            return queue;
        }
    }

}
