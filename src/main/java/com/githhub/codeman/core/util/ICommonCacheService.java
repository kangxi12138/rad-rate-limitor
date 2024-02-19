package com.githhub.codeman.core.util;

import java.util.concurrent.TimeUnit;

public interface ICommonCacheService {
    void set(String var1, String var2);

    void set(String var1, String var2, long var3);

    String get(String var1);

    boolean contains(String var1);

    void expire(String var1, long var2, TimeUnit var4);

    void remove(String var1);

    long ttl(String var1);

    void expireAt(String var1, long var2);

    long expireAt(String var1);
}
