package com.hydra.android.mosbytest.presenter;

import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.google.gson.Gson;
import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;
import com.hydra.android.mosbytest.model.Timer;
import com.hydra.android.mosbytest.model.TimerHandler;
import com.hydra.android.mosbytest.model.TimerHandlerListener;
import com.hydra.android.mosbytest.view.TimerView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by jslapnicka on 1.12.2015.
 */
public class TimerPresenter extends MvpBasePresenter<TimerView> implements TimerHandlerListener {

    private final String TAG = "TimerPresenter";
    private TimerHandler mHandler;
    private Timer timer;

    private void cancelHandlerIfRunning() {
        if (mHandler != null) {
            mHandler.removeMessages(TimerHandler.MSG_UPDATE_TIMER);
            mHandler = null;
        }
    }

    public void start() {
        Log.i(TAG, "start()");
        cancelHandlerIfRunning();

        if (mHandler == null) {
            mHandler = new TimerHandler(Looper.getMainLooper(), this);
            if (getTimer() != null)
                mHandler.setTimer(getTimer());
        }

        if (isViewAttached()) {
            mHandler.sendEmptyMessage(TimerHandler.MSG_START_TIMER);
        }
    }

    public void resume() {
        Log.i(TAG, "resume()");

        if (mHandler == null) {
            mHandler = new TimerHandler(Looper.getMainLooper(), this);
            if (getTimer() != null)
                mHandler.setTimer(getTimer());
        }

        if (isViewAttached()) {
            mHandler.sendEmptyMessage(TimerHandler.MSG_RESUME_TIMER);
        }
    }

    public void stop() {
        Log.i(TAG, "stop()");

        if (isViewAttached() && mHandler != null) {
            mHandler.sendEmptyMessage(TimerHandler.MSG_STOP_TIMER);
        } else if (isViewAttached() && mHandler == null) {
            mHandler = new TimerHandler(Looper.getMainLooper(), this);
            if (getTimer() != null)
                mHandler.setTimer(getTimer());
            mHandler.sendEmptyMessage(TimerHandler.MSG_STOP_TIMER);
        }
    }

    public void reset() {
        Log.i(TAG, "reset()");

        if (isViewAttached() && mHandler != null) {
            mHandler.sendEmptyMessage(TimerHandler.MSG_RESET_TIMER);
        }
    }

    public boolean isTimerRunning() {
        return (mHandler == null ? false : mHandler.getTimer().isRunning());
    }

    public boolean isTimerStopped() {
        return (mHandler == null ? false : mHandler.getTimer().isStopped());
    }

    public void saveTimerObjectToFile(Context context) {
        Timer timer;
        if (mHandler != null) {
            timer = mHandler.getTimer();
            if (timer != null) {
                //File internalDir = context.getFilesDir();
                //File saveFile = new File(internalDir.getAbsolutePath() + "/savedtimer.bin");
                Gson gson = new Gson();
                String json = gson.toJson(timer);
                Log.i(TAG, "Saving timer to JSON: " + json);

                FileOutputStream stream;
                try {
                    stream = context.openFileOutput("savedtimer.json", Context.MODE_PRIVATE);
                    stream.write(json.getBytes());
                    stream.close();
                } catch (IOException ex) {
                    Log.e(TAG, "Error while writing to savedtimer file");
                    ex.printStackTrace();
                }
            }
        }
    }

    public void loadTimerObjectFromFile(Context context) {
        FileInputStream stream;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        try {
            stream = context.openFileInput("savedtimer.json");
            inputStreamReader = new InputStreamReader(stream);
            bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
            stream.close();
            inputStreamReader.close();
            bufferedReader.close();
            context.deleteFile("savedtimer.json");
            Log.i(TAG, "Loaded file: " + builder.toString());

            setTimer(fromJsonToTimer(builder.toString()));
            resumeSavedState();
        } catch (IOException ex) {
            Log.e(TAG, "Error while reading from savedtimer file");
            ex.printStackTrace();
        }
    }

    private Timer fromJsonToTimer(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Timer.class);
    }

    private void resumeSavedState() {
        if (getTimer().isRunning()) {
            if (getTimer().isStopped()) {
                getView().showStopped(true);
            } else {
                getView().showResumed(true);
            }
        } else {
            getView().resetTime();
        }
    }

    private void setTimer(Timer loadedTimer) {
        this.timer = loadedTimer;
    }

    private Timer getTimer() {
        return this.timer;
    }

    @Override
    public void onTimerUpdate(String time) {
        if (isViewAttached())
            getView().showTime(time);
    }

}
