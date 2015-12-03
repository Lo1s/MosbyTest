package com.hydra.android.mosbytest.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hydra.android.mosbytest.model.StopWatch;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public interface TimerView extends MvpView {

    void showRunning(StopWatch timer, boolean wasDestroyed);
    void showStopped(StopWatch timer, boolean wasDestroyed);
    void showPaused(StopWatch timer, boolean wasDestroyed);
    void showTime(String time);
    void setTimerData(StopWatch timer);
}
