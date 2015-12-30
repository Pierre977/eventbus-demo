package com.example.eventbusdemo.classic.controller;

import android.view.View;
import android.widget.TextView;

import com.example.eventbusdemo.R;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultSecondViewControllerImpl implements SecondViewController {

    private TextView coordinatesLabel;

    @Override
    public void setupView(View view) {
        coordinatesLabel = (TextView) view.findViewById(R.id.location_coordinates);
        coordinatesLabel.setText("I am waiting for location...");
    }

    @Override
    public void displayCoordinates(double lat, double lng) {
        coordinatesLabel.setText("Coordinates: lat " + Double.toString(lat) + " long " + Double.toString(lng));
    }

}
