package com.githhub.codeman.core.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

public class ObjectUtil {
    public static boolean isEmpty(Object object) {
        if (isNull(object)) {
            return true;
        } else if (object instanceof String) {
            String string = (String)object;
            return StringUtil.isEmpty(string);
        } else if (object instanceof Collection) {
            Collection collection = (Collection)object;
            return CollectionUtil.isEmpty(collection);
        } else if (object instanceof Map) {
            Map map = (Map)object;
            return MapUtil.isEmpty(map);
        } else if (object.getClass().isArray()) {
            return Array.getLength(object) == 0;
        } else {
            return false;
        }
    }
    public static boolean isNull(Object object) {
        return null == object;
    }
}
