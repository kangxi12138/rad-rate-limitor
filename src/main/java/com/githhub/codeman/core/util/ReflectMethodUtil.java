package com.githhub.codeman.core.util;

import java.lang.reflect.Method;

public class ReflectMethodUtil {
    public static String getMethodFullName(Method method) {
        if (method == null) {
            return "null";
        } else {
            String className = method.getDeclaringClass().getName();
            Class[] types = method.getParameterTypes();
            StringBuilder nameBuilder = new StringBuilder(className + "." + method.getName());
            if (!ObjectUtil.isEmpty(types)) {
                Class[] var4 = types;
                int var5 = types.length;

                for(int var6 = 0; var6 < var5; ++var6) {
                    Class parameter = var4[var6];
                    nameBuilder.append(":").append(parameter.getName());
                }
            }

            return nameBuilder.toString();
        }
    }
}
