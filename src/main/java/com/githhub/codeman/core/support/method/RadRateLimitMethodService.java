package com.githhub.codeman.core.support.method;




import com.githhub.codeman.core.util.ReflectMethodUtil;

import java.lang.reflect.Method;



public class RadRateLimitMethodService implements com.githhub.codeman.api.support.RadRateLimitMethodService {

    @Override
    public String getMethodId(Method method, Object[] params) {
        // 是否需要包含 class?
        return ReflectMethodUtil.getMethodFullName(method);
    }

}
