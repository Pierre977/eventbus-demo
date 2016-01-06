package com.example.eventbusdemo.witheventbus.controller;

import android.view.View;
import android.widget.TextView;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.controller.ThirdViewController;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultThirdViewControllerImpl implements ThirdViewController {

    private TextView logText;

    @Override
    public void setupView(View view) {
        logText = (TextView) view.findViewById(R.id.asynctask_logger);
    }

    @Override
    public void displayLogText(String message) {
        logText.setText(message);
    }

}
