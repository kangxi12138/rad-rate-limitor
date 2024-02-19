package com.githhub.codeman.api.support;


import com.githhub.codeman.api.core.RadRateLimitContext;
import com.githhub.codeman.api.dto.RadRateLimitConfig;

import java.util.List;

public interface RadRateLimitRejectListenerContext extends RadRateLimitContext {

    String tokenId();

    String methodId();

    List<RadRateLimitConfig> configList();


    boolean acquireFlag();

}
