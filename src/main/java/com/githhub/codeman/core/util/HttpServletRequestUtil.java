//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.githhub.codeman.core.util;


import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public final class HttpServletRequestUtil {
    private HttpServletRequestUtil() {
    }

    /** @deprecated */
    @Deprecated
    public static String getIp(HttpServletRequest req) {
        String ip = req.getHeader("X-Forwarded-For");
        if (StringUtil.isEmpty(ip)) {
            ip = req.getHeader("Proxy-Client-IP");
        }

        if (StringUtil.isEmpty(ip)) {
            ip = req.getHeader("WL-Proxy-Client-IP");
        }

        if (StringUtil.isEmpty(ip)) {
            ip = req.getRemoteAddr();
        }

        return ip;
    }

    public static Map<String, String> getParamMap(HttpServletRequest request) {
        Map<String, String> resultMap = new HashMap();
        Enumeration<String> paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()) {
            String paramName = (String)paramNames.nextElement();
            String paramValue = request.getParameter(paramName);
            resultMap.put(paramName, paramValue);
        }

        return resultMap;
    }
}
