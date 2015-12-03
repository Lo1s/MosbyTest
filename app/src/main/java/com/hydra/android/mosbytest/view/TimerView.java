package com.hydra.android.mosbytest.view;

import com.hannesdorfmann.mosby.mvp.MvpView;
import com.hydra.android.mosbytest.model.Timer;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public interface TimerView extends MvpView {

    void showRunning(Timer timer, boolean wasDestroyed);
    void showStopped(Timer timer, boolean wasDestroyed);
    void showPaused(Timer timer, boolean wasDestroyed);
    void showTime(String time);
}
