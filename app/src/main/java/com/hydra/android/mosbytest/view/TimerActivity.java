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
import com.hydra.android.mosbytest.model.StopWatch;
import com.hydra.android.mosbytest.presenter.TimerPresenter;

public class TimerActivity extends MvpViewStateActivity<TimerView, TimerPresenter>
        implements TimerView {


    private TextView timerTextView;
    private Button startButton;
    private Button stopButton;
    private TimerViewState vs;

    private void initLayout() {
        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch (id) {
                    case R.id.startButton:
                        showRunning(null, false);
                        break;

                    case R.id.stopButton:
                        showStopped(null, false);
                        break;
                }
            }
        };

        startButton = (Button) findViewById(R.id.startButton);
        startButton.setOnClickListener(buttonListener);
        stopButton = (Button) findViewById(R.id.stopButton);
        stopButton.setOnClickListener(buttonListener);
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
    protected void onDestroy() {
        super.onDestroy();
        presenter.saveTimerObjectToFile(this);
    }

    @Override
    public void showTime(String time) {
        timerTextView.setText(time);
    }

    @Override
    public void showStopped(StopWatch timer, boolean wasDestroyed) {
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerStopped();

        presenter.stop();
    }

    @Override
    public void showRunning(StopWatch timer, boolean wasDestroyed) {
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerRunning();

        if (isRestoringViewState() || wasDestroyed) {
            //animation here
            if (timer != null)
                presenter.resume(timer);
        } else {
            presenter.start();
        }
    }

    @Override
    public void showPaused(StopWatch timer, boolean wasDestroyed) {
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerPaused();

        presenter.pause();
    }

    @Override
    public void setTimerData(StopWatch timer) {
        if (vs == null)
            vs = (TimerViewState) viewState;
        vs.setTimerData(timer);
    }
}
