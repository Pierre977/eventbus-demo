package com.example.eventbusdemo.classic.controller;

import android.view.View;

import com.example.eventbusdemo.classic.callback.FragmentChangeCallback;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public interface FirstViewController {

    void setupViews(View view, ToolbarColorChangeCallback colorChangeCallback, FragmentChangeCallback fragmentChangeCallback);

}
