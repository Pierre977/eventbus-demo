package com.example.eventbusdemo.classic.controller;

import android.view.View;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public interface SecondViewController {
    void setupView(View view);

    void displayCoordinates(double lat, double lng);
}
