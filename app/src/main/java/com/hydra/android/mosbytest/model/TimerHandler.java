package com.hydra.android.mosbytest.model;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class TimerHandler extends Handler {

    public static final int MSG_START_TIMER = 0;
    public static final int MSG_STOP_TIMER = 1;
    public static final int MSG_RESUME_TIMER = 2;
    public static final int MSG_RESET_TIMER = 3;
    public static final int MSG_UPDATE_TIMER = 4;

    private final int REFRESH_RATE = 100;

    private Timer timer;
    private TimerHandlerListener listener;

    public TimerHandler(Looper looper, TimerHandlerListener listener) {
        super(looper);
        this.listener = listener;

        if (timer == null)
            this.timer = new Timer();
    }

    public Timer getTimer() {
        return timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }

    @Override
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case MSG_START_TIMER:
                timer.start();
                sendEmptyMessage(MSG_UPDATE_TIMER);
                break;
            case MSG_STOP_TIMER:
                timer.stop();
                listener.onTimerUpdate(timer.getElapsedTime() + "");
                removeMessages(MSG_UPDATE_TIMER);
                break;
            case MSG_RESUME_TIMER:
                timer.resume();
                if (!timer.isStopped()) {
                    sendEmptyMessage(MSG_UPDATE_TIMER);
                }
                break;
            case MSG_RESET_TIMER:
                timer.reset();
                listener.onTimerUpdate(timer.getElapsedTimeMili() + "");
                removeMessages(MSG_UPDATE_TIMER);
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
