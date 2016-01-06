package com.example.eventbusdemo.witheventbus.event;

import android.location.Location;

/**
 * Created by petnagy on 2015. 12. 31..
 */
public interface OnLocationChangeListener {

    void onEvent(Location location);

}
