package com.hydra.android.mosbytest.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class TimerHandler extends Handler {

    public static final int MSG_START_TIMER = 0;
    public static final int MSG_PAUSE_TIMER = 1;
    public static final int MSG_RESUME_TIMER = 2;
    public static final int MSG_STOP_TIMER = 3;
    public static final int MSG_UPDATE_TIMER = 4;

    private final int REFRESH_RATE = 100;

    private StopWatch timer;
    private TimerHandlerListener listener;

    public TimerHandler(Looper looper, TimerHandlerListener listener, StopWatch timer) {
        super(looper);
        this.listener = listener;

        if (timer == null)
            this.timer = new StopWatch();
        else
            this.timer = timer;
    }

    public StopWatch getTimer() {
        return timer;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case MSG_START_TIMER:
                timer.start();
                listener.passTimerObject(this.timer);
                sendEmptyMessage(MSG_UPDATE_TIMER);
                break;
            case MSG_PAUSE_TIMER:
                timer.pause();
                listener.onTimerUpdate(timer.getElapsedTimeMili() + "");
                break;
            case MSG_RESUME_TIMER:
                timer.resume();
                sendEmptyMessage(MSG_UPDATE_TIMER);
                break;
            case MSG_STOP_TIMER:
                removeMessages(MSG_UPDATE_TIMER);
                timer.stop();
                listener.onTimerUpdate(timer.getElapsedTimeMili() + "");
                break;
            case MSG_UPDATE_TIMER:
                listener.onTimerUpdate(timer.getElapsedTimeMili() + "");
                sendEmptyMessageDelayed(MSG_UPDATE_TIMER, REFRESH_RATE);
                break;
            default:
                break;
        }
    }
}
