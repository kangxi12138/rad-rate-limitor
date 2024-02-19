package com.githhub.codeman.api.support;

import java.lang.reflect.Method;

public interface RadRateLimitMethodService {

    String getMethodId(final Method method, final Object[] params);

}
