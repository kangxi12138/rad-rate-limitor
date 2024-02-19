package com.githhub.codeman.core.dto;

import java.io.Serializable;


public class RateLimitLeakyBucketDto implements Serializable {

    
    private long rate;

    
    private long capacity;

    
    private volatile long water;

    
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

    public long getWater() {
        return water;
    }

    public void setWater(long water) {
        this.water = water;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "RateLimitLeakyBucketDto{" +
                "rate=" + rate +
                ", capacity=" + capacity +
                ", water=" + water +
                ", lastUpdateTime=" + lastUpdateTime +
                '}';
    }

}
