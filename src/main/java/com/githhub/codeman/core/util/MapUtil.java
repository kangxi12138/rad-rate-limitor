package com.githhub.codeman.core.util;

import java.util.Map;

public class MapUtil {
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || 0 == map.size();
    }
}
