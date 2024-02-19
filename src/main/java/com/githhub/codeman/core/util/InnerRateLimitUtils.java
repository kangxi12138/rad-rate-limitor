package com.githhub.codeman.core.util;


import com.githhub.codeman.api.dto.RadRateLimitConfig;


public class InnerRateLimitUtils {

    private InnerRateLimitUtils(){}

    /**
     * 计算速率
     * @param configDto 配置
     * @return 结果
     */
    public static Long calcRate(RadRateLimitConfig configDto) {
        // 暂不考虑特殊输入，比如 1s 令牌少于1 的场景
        long intervalSeconds = configDto.getTimeUnit().toSeconds(configDto.getInterval());
        // 速率
        return Math.max(1, configDto.getCount() / intervalSeconds);
    }

}
