package com.githhub.codeman.api.dto;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;


public class RadRateLimitConfig implements Serializable {


    private int permits;


    private TimeUnit timeUnit;

    private long interval;


    private Long count;


    private boolean enable;

    public int getPermits() {
        return permits;
    }

    public void setPermits(int permits) {
        this.permits = permits;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "RateLimitConfigDto{" +
                "permits=" + permits +
                ", timeUnit=" + timeUnit +
                ", interval=" + interval +
                ", count=" + count +
                ", enable=" + enable +
                '}';
    }

}
