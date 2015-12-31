package com.example.eventbusdemo.classic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.callback.FragmentChangeCallback;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;
import com.example.eventbusdemo.classic.controller.DefaultFragmentControllerImpl;
import com.example.eventbusdemo.classic.controller.FragmentController;
import com.example.eventbusdemo.classic.dataset.Color;

public class MainActivity extends AppCompatActivity implements ToolbarColorChangeCallback {

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
    }

}
