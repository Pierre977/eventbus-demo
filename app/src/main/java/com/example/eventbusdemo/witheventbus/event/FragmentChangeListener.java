package com.example.eventbusdemo.witheventbus.event;

import com.example.eventbusdemo.dataset.InnerFragment;

/**
 * Created by petnagy on 2015. 12. 31..
 */
public interface FragmentChangeListener {

    void onEvent(InnerFragment innerFragment);

}
