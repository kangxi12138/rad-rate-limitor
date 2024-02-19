package com.githhub.codeman.api.support;


import com.githhub.codeman.api.dto.RadRateLimitConfig;

import java.lang.reflect.Method;
import java.util.List;

public interface RadRateLimitConfigService {


    List<RadRateLimitConfig> queryConfigList(String tokenId,
                                             String methodId,
                                             Method method);

}
