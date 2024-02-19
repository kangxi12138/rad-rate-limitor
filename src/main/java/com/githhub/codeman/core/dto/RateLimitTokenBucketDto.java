package com.githhub.codeman.core.dto;

import java.io.Serializable;


public class RateLimitTokenBucketDto implements Serializable {

    
    private long rate;

    
    private long capacity;

    
    private volatile long tokenNum;

    
    private volatile long lastUpdateTime;

    public long getRate() {
        return rate;
    }

    public void setRate(long rate) {
        this.rate = rate;
    }

    public long getCapacity() {
        return capacity;
    }

    public void setCapacity(long capacity) {
        this.capacity = capacity;
    }

    public long getTokenNum() {
        return tokenNum;
    }

    public void setTokenNum(long tokenNum) {
        this.tokenNum = tokenNum;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "RateLimitBucketDto{" +
                "rate=" + rate +
                ", capacity=" + capacity +
                ", tokenNum=" + tokenNum +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

}
