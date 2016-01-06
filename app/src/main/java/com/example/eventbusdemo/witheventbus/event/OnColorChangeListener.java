package com.example.eventbusdemo.witheventbus.event;

import com.example.eventbusdemo.dataset.Color;

/**
 * Created by petnagy on 2015. 12. 31..
 */
public interface OnColorChangeListener {

    void onEvent(Color color);
}
