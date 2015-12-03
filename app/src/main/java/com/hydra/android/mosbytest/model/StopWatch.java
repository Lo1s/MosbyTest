package com.hydra.android.mosbytest.model;

/*
Copyright (c) 2005, Corey Goldberg

StopWatch.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.

Modified: Bilal Rabbani bilalrabbani1@live.com (Nov 2013)
*/

import android.os.Parcel;
import android.os.Parcelable;

public class StopWatch implements Parcelable {
    private long startTime = 0;
    private boolean running = false;
    private long currentTime = 0;
    private static final int NUMBER_OF_ARGS = 3;

    public StopWatch() {

    }

    public void start() {
        this.startTime = System.currentTimeMillis();
        this.running = true;
    }

    public void stop() {
        this.running = false;
    }

    public void pause() {
        this.running = false;
        currentTime = System.currentTimeMillis() - startTime;
    }

    public void resume() {
        this.running = true;

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

    public String toString() {
        return "test";
    }

    // Parcelable
    public StopWatch(Parcel in) {
        String[] data = new String[NUMBER_OF_ARGS];

        in.readStringArray(data);
        this.startTime = Long.valueOf(data[0]);
        this.running = Boolean.valueOf(data[1]);
        this.currentTime = Long.valueOf(data[2]);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeStringArray(new String[] {
                String.valueOf(this.startTime),
                String.valueOf(this.running),
                String.valueOf(this.currentTime)});
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        @Override
        public Object createFromParcel(Parcel source) {
            return new StopWatch(source);
        }

        @Override
        public Object[] newArray(int size) {
            return new StopWatch[size];
        }
    };
}
