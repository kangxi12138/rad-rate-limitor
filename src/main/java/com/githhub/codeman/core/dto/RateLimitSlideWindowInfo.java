package com.githhub.codeman.core.dto;

import java.io.Serializable;
import java.util.List;


public class RateLimitSlideWindowInfo implements Serializable {

    
    private long initTime;

    
    private List<RateLimitSlideWindowDto> windowList;

    public long getInitTime() {
        return initTime;
    }

    public void setInitTime(long initTime) {
        this.initTime = initTime;
    }

    public List<RateLimitSlideWindowDto> getWindowList() {
        return windowList;
    }

    public void setWindowList(List<RateLimitSlideWindowDto> windowList) {
        this.windowList = windowList;
    }

    @Override
    public String toString() {
        return "RateLimitSlideWindowInfo{" +
                "initTime=" + initTime +
                ", windowList=" + windowList +
                '}';
    }

}
