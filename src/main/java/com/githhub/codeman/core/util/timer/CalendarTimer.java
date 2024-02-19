package com.githhub.codeman.core.util.timer;

import java.util.Calendar;

public class CalendarTimer implements ITimer {
    private static final CalendarTimer INSTANCE = new CalendarTimer();

    public CalendarTimer() {
    }

    public static CalendarTimer getInstance() {
        return INSTANCE;
    }

    public long time() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
