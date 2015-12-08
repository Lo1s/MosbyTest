package com.hydra.android.mosbytest.view;

import com.hannesdorfmann.mosby.mvp.MvpView;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public interface TimerView extends MvpView {

    void showStarted(boolean wasDestroyed);
    void showResumed(boolean wasDestroyed);
    void showStopped(boolean wasDestroyed);
    void showTime(String time);
    void resetTime();
}
