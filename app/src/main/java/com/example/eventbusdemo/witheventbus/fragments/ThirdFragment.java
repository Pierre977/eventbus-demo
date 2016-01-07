package com.example.eventbusdemo.witheventbus.fragments;

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
import com.example.eventbusdemo.witheventbus.event.OnBackgroundThreadEventListener;

import de.greenrobot.event.EventBus;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class ThirdFragment extends Fragment implements OnBackgroundThreadEventListener {

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

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onEventMainThread(String message) {
        displayLogMessage(message);
    }

    private class LongRunningAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            EventBus.getDefault().post("\n Emulated network process finished");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            EventBus.getDefault().post("\n Emulated network result processing process finished");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            EventBus.getDefault().post("\n Emulated persist process finished");
            return null;
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
