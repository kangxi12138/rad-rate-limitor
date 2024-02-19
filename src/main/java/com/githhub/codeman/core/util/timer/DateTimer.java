package com.githhub.codeman.core.util.timer;

import java.util.Date;

public class DateTimer implements ITimer {
    private static final DateTimer INSTANCE = new DateTimer();

    public DateTimer() {
    }

    public static DateTimer getInstance() {
        return INSTANCE;
    }

    public long time() {
        return (new Date()).getTime();
    }
}
