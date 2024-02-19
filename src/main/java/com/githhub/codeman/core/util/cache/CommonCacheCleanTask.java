package com.githhub.codeman.core.util.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Iterator;
import java.util.Map;

public class CommonCacheCleanTask implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(CommonCacheCleanTask.class);
    private final Map<String, CommonCacheValueDto> map;

    public CommonCacheCleanTask(Map<String, CommonCacheValueDto> map) {
        //记得判断
        this.map = map;
    }

    public void run() {
        LOG.info("[Cache] 开始清理过期数据");
        long currentMills = System.currentTimeMillis();
        Iterator var3 = this.map.entrySet().iterator();

        while(var3.hasNext()) {
            Map.Entry<String, CommonCacheValueDto> entry = (Map.Entry)var3.next();
            Long expireTime = ((CommonCacheValueDto)entry.getValue()).getExpireTime();
            if (expireTime != null && currentMills >= expireTime) {
                String key = (String)entry.getKey();
                this.map.remove(key);
                LOG.info("[Cache] 移除 key: {}", new Object[]{key});
            }
        }

        LOG.info("[Cache] 结束清理过期数据");
    }
}
