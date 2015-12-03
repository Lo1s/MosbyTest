package com.hydra.android.mosbytest.view;

import android.os.Parcelable;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hydra.android.mosbytest.model.Timer;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public class TimerViewState implements ViewState<TimerView> {

    final int STATE_TIMER_IS_STOPPED = 0;
    final int STATE_TIMER_IS_RUNNING = 1;
    final int STATE_TIMER_IS_PAUSED = 2;

    private final String KEY_TIMER = "TimerViewState-data";

    int state = STATE_TIMER_IS_STOPPED;
    public Parcelable timerData;

    public void setTimerStopped() {
        state = STATE_TIMER_IS_STOPPED;
    }

    public void setTimerRunning() {
        state = STATE_TIMER_IS_RUNNING;
    }

    public void setTimerPaused() {
        state = STATE_TIMER_IS_PAUSED;
    }

    /**
     * Is called from Mosby to apply the view state to the view.
     * We do that by calling the methods from the View interface (like the presenter does)
     */
    @Override
    public void apply(TimerView view, boolean retained) {

        switch (state) {
            case STATE_TIMER_IS_STOPPED:
                view.showStopped((Timer) timerData, false);
                break;
            case STATE_TIMER_IS_RUNNING:
                view.showRunning((Timer) timerData, false);
                break;
            case STATE_TIMER_IS_PAUSED:
                view.showPaused((Timer) timerData, false);
                break;
        }
    }
}
