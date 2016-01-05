package com.example.eventbusdemo.witheventbus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.dataset.Color;
import com.example.eventbusdemo.witheventbus.controller.DefaultFragmentControllerImpl;
import com.example.eventbusdemo.witheventbus.controller.FragmentController;
import com.example.eventbusdemo.witheventbus.event.ColorChangeListener;
import com.example.eventbusdemo.witheventbus.service.LocationService;

import de.greenrobot.event.EventBus;

public class MainActivity extends AppCompatActivity implements ColorChangeListener {

    private FragmentController fragmentController;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (fragmentController == null) {
            fragmentController = new DefaultFragmentControllerImpl(getSupportFragmentManager());
        }
        fragmentController.displayFirstFragment();

        startLocationService();
    }

    private void startLocationService() {
        Intent locationService = new Intent(this, LocationService.class);
        startService(locationService);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        fragmentController.registerEventBus();
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
        fragmentController.unregisterEventBus();
        stopLocationService();
    }

    private void stopLocationService() {
        Intent intent = new Intent(this, LocationService.class);
        stopService(intent);
    }

    @Override
    public void onBackPressed() {
        if (fragmentController.isSecondFragmentActive()) {
            fragmentController.displayFirstFragment();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onEvent(Color color) {
        toolbar.setBackgroundColor(color.getBackgroundColor());
        Log.d("EventBusDemo", "Color Change Event");
    }
}
