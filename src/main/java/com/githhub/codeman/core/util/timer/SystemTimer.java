package com.githhub.codeman.core.util.timer;

public class SystemTimer implements ITimer {
    private static final SystemTimer INSTANCE = new SystemTimer();

    public SystemTimer() {
    }

    public static SystemTimer getInstance() {
        return INSTANCE;
    }

    public long time() {
        return System.currentTimeMillis();
    }
}
