package com.example.eventbusdemo.classic.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.controller.DefaultThirdViewControllerImpl;
import com.example.eventbusdemo.classic.controller.ThirdViewController;

import de.greenrobot.event.EventBus;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class ThirdFragment extends Fragment {

    private static final Integer FIRST_PHASE = 10;

    private static final Integer SECOND_PHASE = 20;

    private static final Integer THIRD_PHASE = 30;

    private ThirdViewController viewController;

    private StringBuilder logMessage;

    public static ThirdFragment newInstance() {
        return new ThirdFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewController == null) {
            viewController = new DefaultThirdViewControllerImpl();
        }

        logMessage = new StringBuilder("Emulate long running Task:");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.third_fragment, container, false);
        viewController.setupView(view);
        viewController.displayLogText(logMessage.toString());
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        new LongRunningAsyncTask().execute();
    }

    private class LongRunningAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(10);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(20);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(30);
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Integer progress = values[0];
            if (FIRST_PHASE.equals(progress)) {
                displayLogMessage("\n Emulated network process finished");
            } else if (SECOND_PHASE.equals(progress)) {
                displayLogMessage("\n Emulated network result processing process finished");
            } else if (THIRD_PHASE.equals(progress)) {
                displayLogMessage("\n Emulated persist process finished");
            }
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            displayLogMessage("\n Emulated long running process finished");
        }
    }

    private void displayLogMessage(String message) {
        logMessage.append(message);
        viewController.displayLogText(logMessage.toString());
    }
}
