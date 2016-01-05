package com.example.eventbusdemo.classic.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.controller.DefaultSecondViewControllerImpl;
import com.example.eventbusdemo.classic.controller.DefaultThirdViewControllerImpl;
import com.example.eventbusdemo.classic.controller.SecondViewController;
import com.example.eventbusdemo.classic.controller.ThirdViewController;
import com.example.eventbusdemo.classic.service.LocationServiceClassic;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class ThirdFragment extends Fragment {

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
}
