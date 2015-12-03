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
    private long startTime = 0;
    private long currentTime = 0;
    private boolean running = false;
    private boolean isStopWatch = true;
    private static final int NUMBER_OF_ARGS = 3;

    public Timer(boolean isStopWatch) {
        this.isStopWatch = isStopWatch;
    }

    public void start() {
        if (isStopWatch) {
            this.startTime = System.currentTimeMillis();
            this.running = true;
        }
    }

    public void stop() {
        if (isStopWatch) {
            this.running = false;
        }
    }

    public void pause() {
        if (isStopWatch) {
            this.running = false;
            currentTime = System.currentTimeMillis() - startTime;
        }
    }

    public void resume() {
        if (isStopWatch) {
            this.running = true;
        }
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

    public long getStartTime() {
        return this.startTime;
    }

    public boolean isRunning() {
        return this.running;
    }

    public long getCurrentTime() {
        return this.currentTime;
    }

    public boolean getTypeOfTimer() {
        return isStopWatch;
    }

    //elaspsed time in milliseconds
    public long getElapsedTimeMili() {
        long elapsed = 0;
        if (running) {
            elapsed =((System.currentTimeMillis() - startTime)/100) % 1000 ;
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
            elapsed = (((System.currentTimeMillis() - startTime) / 1000) / 60 ) % 60;
        }
        return elapsed;
    }

    //elaspsed time in hours
    public long getElapsedTimeHour() {
        long elapsed = 0;
        if (running) {
            elapsed = ((((System.currentTimeMillis() - startTime) / 1000) / 60 ) / 60);
        }
        return elapsed;
    }
}
