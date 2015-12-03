package com.hydra.android.mosbytest.view;

import android.os.Bundle;
import android.os.Parcelable;

import com.hannesdorfmann.mosby.mvp.viewstate.RestorableViewState;
import com.hydra.android.mosbytest.model.StopWatch;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public class TimerViewState implements RestorableViewState<TimerView> {

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

    public void setTimerData(Parcelable in) {
        this.timerData = in;
    }

    @Override
    public void saveInstanceState(Bundle out) {
        out.putParcelable(KEY_TIMER, timerData);
    }

    @Override
    public RestorableViewState<TimerView> restoreInstanceState(Bundle in) {
        if (in == null) {
            return null;
        }

        timerData = in.getParcelable(KEY_TIMER);
        return this;
    }

    /**
     * Is called from Mosby to apply the view state to the view.
     * We do that by calling the methods from the View interface (like the presenter does)
     */
    @Override
    public void apply(TimerView view, boolean retained) {

        switch (state) {
            case STATE_TIMER_IS_STOPPED:
                view.showStopped((StopWatch) timerData, false);
                break;
            case STATE_TIMER_IS_RUNNING:
                view.showRunning((StopWatch) timerData, false);
                break;
            case STATE_TIMER_IS_PAUSED:
                view.showPaused((StopWatch) timerData, false);
                break;
        }
    }
}
