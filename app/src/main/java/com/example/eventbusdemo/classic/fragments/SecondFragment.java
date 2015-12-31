package com.example.eventbusdemo.classic.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.controller.DefaultSecondViewControllerImpl;
import com.example.eventbusdemo.classic.controller.SecondViewController;
import com.example.eventbusdemo.classic.service.LocationServiceClassic;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class SecondFragment extends Fragment {

    private SecondViewController viewController;

    private BroadcastReceiver locationChangeReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Location location = intent.getParcelableExtra("LOCATION");
            viewController.displayCoordinates(location.getLatitude(), location.getLongitude());
        }
    };

    public static SecondFragment newInstance() {
        SecondFragment fragment = new SecondFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewController == null) {
            viewController = new DefaultSecondViewControllerImpl();
        }

        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.location");
        getActivity().registerReceiver(locationChangeReceiver, filter);
        Intent locationService = new Intent(getActivity(), LocationServiceClassic.class);
        getActivity().startService(locationService);
    }

    @Override
    public void onStop() {
        super.onStop();
        Intent intent = new Intent(getActivity(), LocationServiceClassic.class);
        getActivity().stopService(intent);
        getActivity().unregisterReceiver(locationChangeReceiver);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        viewController.setupView(view);
        return view;
    }
}
