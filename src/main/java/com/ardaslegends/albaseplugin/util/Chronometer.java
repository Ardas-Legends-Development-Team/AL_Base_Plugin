package com.ardaslegends.albaseplugin.util;

public class Chronometer {
    private final long nanoSecondsPerMillisecond = 1000000;
    private final long nanoSecondsPerSecond = 1000000000;
    private final long nanoSecondsPerMinute = 60000000000L;
    private final long nanoSecondsPerHour = 3600000000000L;

    private long chronStartTime = 0;
    private long chronPauseTime = 0;
    private long chronStopTime = 0;
    private boolean stopWatchRunning = false;


    public void start() {
        this.chronStartTime = System.nanoTime();
        this.stopWatchRunning = true;
    }

    public void pause() {
        this.chronPauseTime = System.nanoTime();
        this.stopWatchRunning = false;
    }

    public void resume() {
        // We advance the start of the clock to skip the pause time (trimming).
        chronStartTime += (System.nanoTime() - chronPauseTime);
        this.stopWatchRunning = true;
    }

    public void stop() {
        this.chronStopTime = System.nanoTime();
        this.stopWatchRunning = false;
    }


    public long getTotalElapsedMilliseconds() {
        long elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);

        return elapsedTime / nanoSecondsPerMillisecond;
    }

    public long getElapsedMilliseconds() {
        long elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);
        elapsedTime /= nanoSecondsPerMillisecond;
        return elapsedTime - (getTotalElapsedSeconds() * 1000);
    }

    public long getTotalElapsedSeconds() {
        long elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);

        return elapsedTime / nanoSecondsPerSecond;
    }

    public long getElapsedSeconds() {
        long elapsedTime;

        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);
        elapsedTime /= nanoSecondsPerSecond;
        return elapsedTime - (getTotalElapsedMinutes() * 60);
    }


    public long getTotalElapsedMinutes() {
        long elapsedTime;
        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);
        return  elapsedTime / nanoSecondsPerMinute;
    }

    public long getElapsedMinutes() {
        long elapsedTime;
        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);
        elapsedTime /= nanoSecondsPerMinute;
        return elapsedTime - (getElapsedHours() * 60);
    }


    public long getElapsedHours() {
        long elapsedTime;
        if (stopWatchRunning)
            elapsedTime = (System.nanoTime() - chronStartTime);
        else
            elapsedTime = (chronStopTime - chronStartTime);

        return elapsedTime / nanoSecondsPerHour;
    }
}
