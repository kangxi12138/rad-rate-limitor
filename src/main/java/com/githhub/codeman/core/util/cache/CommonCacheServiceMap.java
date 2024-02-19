package com.githhub.codeman.core.util.cache;

import com.githhub.codeman.core.util.ICommonCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CommonCacheServiceMap implements ICommonCacheService {
    private static final Logger LOG = LoggerFactory.getLogger(CommonCacheServiceMap.class);
    private Map<String, CommonCacheValueDto> cacheMap;

    public CommonCacheServiceMap() {
        this.initMap();
        this.initCleanTask();
    }

    protected void initMap() {
        this.cacheMap = new ConcurrentHashMap();
    }

    protected void initCleanTask() {
        Runnable cleanTask = new CommonCacheCleanTask(this.cacheMap);
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(cleanTask, 10L, 10L, TimeUnit.SECONDS);
    }

    public synchronized void set(String key, String value) {
        this.set(key, value, 0L);
    }

    public synchronized void set(String key, String value, long expireMills) {
        long actualMills = 0L;
        if (expireMills <= 0L) {
            LOG.info("过期时间小于0，认为不过期");
        } else {
            long currentMills = System.currentTimeMillis();
            actualMills = currentMills + expireMills;
        }

        CommonCacheValueDto dto = CommonCacheValueDto.of(value, actualMills);
        this.cacheMap.put(key, dto);
    }

    public String get(String key) {
        this.checkExpireAndRemove(key);
        CommonCacheValueDto dto = (CommonCacheValueDto)this.cacheMap.get(key);
        return dto == null ? null : dto.getValue();
    }

    public boolean contains(String key) {
        this.checkExpireAndRemove(key);
        return this.cacheMap.containsKey(key);
    }

    public synchronized void expire(String key, long expireTime, TimeUnit timeUnit) {
        if (this.contains(key)) {
            long currentMills = System.currentTimeMillis();
            long actualMills = currentMills + timeUnit.toMillis(expireTime);
            CommonCacheValueDto dto = (CommonCacheValueDto)this.cacheMap.get(key);
            dto.setExpireTime(actualMills);
            this.cacheMap.put(key, dto);
        }

    }

    public synchronized void remove(String key) {
        this.cacheMap.remove(key);
    }

    public long ttl(String key) {
        this.checkExpireAndRemove(key);
        CommonCacheValueDto dto = (CommonCacheValueDto)this.cacheMap.get(key);
        if (dto == null) {
            return -2L;
        } else {
            Long expireTime = dto.getExpireTime();
            if (expireTime == null) {
                return -1L;
            } else {
                long currentTime = System.currentTimeMillis();
                return expireTime - currentTime;
            }
        }
    }

    public void expireAt(String key, long unixTime) {
        if (this.contains(key)) {
            CommonCacheValueDto dto = (CommonCacheValueDto)this.cacheMap.get(key);
            dto.setExpireTime(unixTime);
            this.cacheMap.put(key, dto);
        }

    }

    public long expireAt(String key) {
        this.checkExpireAndRemove(key);
        CommonCacheValueDto dto = (CommonCacheValueDto)this.cacheMap.get(key);
        if (dto == null) {
            return -2L;
        } else {
            Long expireTime = dto.getExpireTime();
            return expireTime == null ? -1L : expireTime;
        }
    }

    private synchronized void checkExpireAndRemove(String key) {
        CommonCacheValueDto dto = (CommonCacheValueDto)this.cacheMap.get(key);
        if (dto != null) {
            Long expireTime = dto.getExpireTime();
            if (expireTime != null) {
                long currentMills = System.currentTimeMillis();
                if (expireTime <= currentMills) {
                    this.cacheMap.remove(key);
                }
            }

        }
    }
}
