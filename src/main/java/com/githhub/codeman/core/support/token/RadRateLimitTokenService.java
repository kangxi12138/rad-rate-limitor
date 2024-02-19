package com.githhub.codeman.core.support.token;


import com.githhub.codeman.core.util.ArrayUtil;
import com.githhub.codeman.core.util.HttpServletRequestUtil;
import com.githhub.codeman.core.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;


public class RadRateLimitTokenService implements com.githhub.codeman.api.support.RadRateLimitTokenService {

    
    private static final Logger LOG = LoggerFactory.getLogger(RadRateLimitTokenService.class);

    @Override
    public String getTokenId(Object[] params) {
        if(ArrayUtil.isEmpty(params)) {
            LOG.warn("Param is empty, return empty");
            return StringUtil.EMPTY;
        }

        for(Object param : params) {
            if(param instanceof HttpServletRequest) {
                HttpServletRequest request = (HttpServletRequest) param;
                return HttpServletRequestUtil.getIp(request);
            }
        }
        LOG.warn("Param is not found in request, return empty");
        return StringUtil.EMPTY;
    }

}
