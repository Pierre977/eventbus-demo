package com.example.eventbusdemo.witheventbus.event;

/**
 * Created by Peter_Istvan_Nagy on 1/6/2016.
 */
public interface OnBackgroundThreadEventListener {

    void onEventMainThread(String message);

}
