package com.example.eventbusdemo.classic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;
import com.example.eventbusdemo.classic.controller.DefaultFragmentControllerImpl;
import com.example.eventbusdemo.classic.controller.FragmentController;
import com.example.eventbusdemo.dataset.Color;

public class MainActivityClassic extends AppCompatActivity implements ToolbarColorChangeCallback {

    private FragmentController fragmentController;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (fragmentController == null) {
            fragmentController = new DefaultFragmentControllerImpl(getSupportFragmentManager(), this);
        }
        fragmentController.displayFirstFragment();
    }

    @Override
    public void onColorChange(Color color) {
        toolbar.setBackgroundColor(color.getBackgroundColor());
        Log.d("EventBusDemo", "Color Change Callback");
    }

    @Override
    public void onBackPressed() {
        if (fragmentController.isSecondFragmentActive()) {
            fragmentController.displayFirstFragment();
        } else {
            super.onBackPressed();
        }
    }
}
