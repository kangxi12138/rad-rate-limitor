package com.githhub.codeman.core.dto;

import java.io.Serializable;


public class RateLimitSlideWindowDto implements Serializable {

    
    private int count;

    
    private long expireTime;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "RateLimitSlideWindowDto{" +
                "count=" + count +
                ", expireTime=" + expireTime +
                '}';
    }
}
