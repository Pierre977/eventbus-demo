package com.example.eventbusdemo.witheventbus.controller;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public interface FragmentController {

    void displayFirstFragment();

    void displaySecondFragment();

    void displayThirdFragment();

    boolean isFirstFragmentActive();

    void registerEventBus();

    void unregisterEventBus();
}
