package com.bgeiotdev.eval.Others;

import java.util.concurrent.atomic.AtomicLong;

public class TimeUtils {
    private static final AtomicLong LAST_TIME_MS = new AtomicLong();
    public static long uniqueCurrentTimeMS() {
        long now = System.currentTimeMillis();
        while(true) {
            long lastTime = LAST_TIME_MS.get();
            if (lastTime >= now)
                now = lastTime+1;
            if (LAST_TIME_MS.compareAndSet(lastTime, now))
                return now;
        }
    }

    public static String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }

    public static String secondsToString(long pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }
}
