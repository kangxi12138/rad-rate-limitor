package com.githhub.codeman.core.support.reject;


import com.githhub.codeman.api.support.RadRateLimitRejectListener;
import com.githhub.codeman.api.support.RadRateLimitRejectListenerContext;
import com.githhub.codeman.core.exception.RateLimitRuntimeException;


public class RateLimitRejectListenerException implements RadRateLimitRejectListener {

    @Override
    public void listen(RadRateLimitRejectListenerContext context) {
        boolean acquireFlag = context.acquireFlag();

        if(!acquireFlag) {
            throw new RateLimitRuntimeException();
        }
    }

}
