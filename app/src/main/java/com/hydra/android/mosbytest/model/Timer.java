package com.hydra.android.mosbytest.model;

/*
Copyright (c) 2005, Corey Goldberg

Timer.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

Modified: Bilal Rabbani bilalrabbani1@live.com (Nov 2013)
*/


public class Timer {
    private long hour;
    private long minute;
    private long second;
    private long startTime = 0;
    private long elapsedTime = 0;
    private long currentTime = 0;
    private boolean running = false;
    private boolean stopped = false;
    private boolean isStopWatch = true; // if false - countdown timer is running instead

    public void start() {
        if (isStopWatch) {
            this.startTime = System.currentTimeMillis();
            this.running = true;
            this.stopped = false;
        } else {
            if (!isStopped()) {
                this.startTime = System.currentTimeMillis()
            }
        }
    }

    public void resume() {
        if (isStopWatch) {
            if (isStopped()) {
                this.startTime = System.currentTimeMillis() - elapsedTime;
                this.stopped = false;
            }
            this.running = true;
        }
    }

    public void stop() {
        if (isStopWatch) {
            if (!isStopped() && isRunning())
                this.elapsedTime = System.currentTimeMillis() - startTime;
            this.stopped = true;
            this.running = true;
        }
    }

    public void reset() {
        if (isStopWatch) {
            this.running = false;
            this.stopped = false;
            this.isStopWatch = true;
            this.startTime = 0;
            this.elapsedTime = 0;
        }
    }

    public void setCountDownTime(long hour, long minute, long second) {

    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setRunning(boolean isRunning) {
        this.running = isRunning;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void setTypeOfTimer(boolean isStopWatch) {
        this.isStopWatch = isStopWatch;
    }

    public long getStartTime() {
        return this.startTime;
    }

    public boolean isRunning() {
        return this.running;
    }

    public boolean isStopped() {
        return this.stopped;
    }

    public long getCurrentTime() {
        return this.currentTime;
    }

    public boolean getTypeOfTimer() {
        return isStopWatch;
    }

    public long getElapsedTime() {
        return (elapsedTime / 100) % 1000;
    }

    //elaspsed time in milliseconds
    public long getElapsedTimeMili() {
        long elapsed = 0;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 100) % 1000;
        }
        return elapsed;
    }

    //elaspsed time in seconds
    public long getElapsedTimeSecs() {
        long elapsed = 0;
        if (running) {
            elapsed = ((System.currentTimeMillis() - startTime) / 1000) % 60;
        }
        return elapsed;
    }

    //elaspsed time in minutes
    public long getElapsedTimeMin() {
        long elapsed = 0;
        if (running) {
            elapsed = (((System.currentTimeMillis() - startTime) / 1000) / 60) % 60;
        }
        return elapsed;
    }

    //elaspsed time in hours
    public long getElapsedTimeHour() {
        long elapsed = 0;
        if (running) {
            elapsed = ((((System.currentTimeMillis() - startTime) / 1000) / 60) / 60);
        }
        return elapsed;
    }
}
