package com.example.eventbusdemo.witheventbus.fragments;

import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.witheventbus.controller.DefaultSecondViewControllerImpl;
import com.example.eventbusdemo.witheventbus.controller.SecondViewController;
import com.example.eventbusdemo.witheventbus.event.LocationChangeListener;

import de.greenrobot.event.EventBus;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class SecondFragment extends Fragment implements LocationChangeListener {

    private SecondViewController viewController;

    public static SecondFragment newInstance() {
        return new SecondFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewController == null) {
            viewController = new DefaultSecondViewControllerImpl();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().registerSticky(this);
        //EventBus.getDefault().getStickyEvent(Location.class);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.second_fragment, container, false);
        viewController.setupView(view);
        return view;
    }

    @Override
    public void onEvent(Location location) {
        Log.d("EventBusDemo", "LocationChange Event");
        viewController.displayCoordinates(location.getLatitude(), location.getLongitude());
    }
}
