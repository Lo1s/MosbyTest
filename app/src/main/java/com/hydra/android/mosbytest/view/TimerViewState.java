package com.hydra.android.mosbytest.view;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public class TimerViewState implements ViewState<TimerView> {

    final int STATE_TIMER_IS_STOPPED = 0;
    final int STATE_TIMER_IS_RUNNING = 1;
    final int STATE_TIMER_IS_PAUSED = 2;

    int state = STATE_TIMER_IS_STOPPED;

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
                view.resetTime();
                break;
            case STATE_TIMER_IS_RUNNING:
                view.showStarted(false);
                break;
            case STATE_TIMER_IS_PAUSED:
                view.showPaused(false);
                break;
        }
    }
}
