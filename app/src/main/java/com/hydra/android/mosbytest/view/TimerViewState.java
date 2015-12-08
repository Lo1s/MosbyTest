package com.hydra.android.mosbytest.view;

import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public class TimerViewState implements ViewState<TimerView> {

    final int STATE_TIMER_IS_STOPPED = 0;
    final int STATE_TIMER_IS_RUNNING = 1;
    final int STATE_TIMER_IS_RESETED = 2;

    int state = STATE_TIMER_IS_STOPPED;

    public void setTimerStopped() {
        state = STATE_TIMER_IS_STOPPED;
    }

    public void setTimerRunning() {
        state = STATE_TIMER_IS_RUNNING;
    }

    public void setTimerReseted() {
        state = STATE_TIMER_IS_RESETED;
    }


    /**
     * Is called from Mosby to apply the view state to the view.
     * We do that by calling the methods from the View interface (like the presenter does)
     */
    @Override
    public void apply(TimerView view, boolean retained) {

        switch (state) {
            case STATE_TIMER_IS_STOPPED:
                view.showStopped(false);
                break;
            case STATE_TIMER_IS_RUNNING:
                view.showResumed(false);
                break;
            case STATE_TIMER_IS_RESETED:
                view.resetTime();
                break;
        }
    }
}
