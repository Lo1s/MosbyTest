package com.hydra.android.mosbytest.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hannesdorfmann.mosby.mvp.viewstate.MvpViewStateActivity;
import com.hannesdorfmann.mosby.mvp.viewstate.ViewState;
import com.hydra.android.mosbytest.R;
import com.hydra.android.mosbytest.presenter.TimerPresenter;

public class TimerActivity extends MvpViewStateActivity<TimerView, TimerPresenter>
        implements TimerView {


    private TextView timerTextView;
    private Button startStopButton;
    private Button resetButton;
    private TimerViewState vs;

    private void initLayout() {
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.startStopButton:
                        if (presenter.isTimerRunning()) {
                            if (presenter.isTimerStopped()) {
                                showResumed(false);
                            } else {
                                showStopped(false);
                            }
                        } else {
                            showStarted(false);
                        }
                        break;
                    case R.id.resetButton:
                        resetTime();
                        break;
                }
            }
        };
        startStopButton = (Button) findViewById(R.id.startStopButton);
        startStopButton.setOnClickListener(buttonListener);
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(buttonListener);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
    }

    @Override
    public ViewState<TimerView> createViewState() {
        return new TimerViewState();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        setRetainInstance(true);
        initLayout();
    }

    @NonNull
    @Override
    public TimerPresenter createPresenter() {
        return new TimerPresenter();
    }

    @Override
    public void onNewViewStateInstance() {
        Log.i("TimerActivity", "onNewViewStateInstance()");
        presenter.loadTimerObjectFromFile(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.saveTimerObjectToFile(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void showTime(String time) {
        timerTextView.setText(time);
    }


    @Override
    public void showStarted(boolean wasDestroyed) {
        Log.i("Destroyed", wasDestroyed + "");
        startStopButton.setText("Stop");
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerRunning();
        if (isRestoringViewState() || wasDestroyed) {
            //animation here
            showResumed(wasDestroyed);
        } else {
            presenter.start();
        }
    }

    @Override
    public void showResumed(boolean wasDestroyed) {
        startStopButton.setText("Stop");
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerRunning();
        presenter.resume();
    }

    @Override
    public void showStopped(boolean wasDestroyed) {
        startStopButton.setText("Start");
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerStopped();
        presenter.stop();
    }

    @Override
    public void resetTime() {
        startStopButton.setText("Start");
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerReseted();

        presenter.reset();
    }
}
