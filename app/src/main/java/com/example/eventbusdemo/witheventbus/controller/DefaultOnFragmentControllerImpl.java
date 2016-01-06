package com.example.eventbusdemo.witheventbus.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.witheventbus.event.OnFragmentChangeListener;
import com.example.eventbusdemo.dataset.InnerFragment;
import com.example.eventbusdemo.witheventbus.fragments.FirstFragment;
import com.example.eventbusdemo.witheventbus.fragments.SecondFragment;
import com.example.eventbusdemo.witheventbus.fragments.ThirdFragment;

import de.greenrobot.event.EventBus;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultOnFragmentControllerImpl implements FragmentController, OnFragmentChangeListener {

    private static final String TAG_FIRST = "firstFragment";

    private static final String TAG_SECOND = "secondFragment";

    private static final String TAG_THIRD = "thirdFragment";

    private FragmentManager fragmentManager;

    public DefaultOnFragmentControllerImpl(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    @Override
    public void displayFirstFragment() {
        replaceFragment(FirstFragment.newInstance(), TAG_FIRST);
    }

    @Override
    public void displaySecondFragment() {
        replaceFragment(SecondFragment.newInstance(), TAG_SECOND);
    }

    @Override
    public void displayThirdFragment() {
        replaceFragment(ThirdFragment.newInstance(), TAG_THIRD);
    }

    @Override
    public boolean isFirstFragmentActive() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_FIRST);
        return fragment != null;
    }

    @Override
    public void registerEventBus() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void unregisterEventBus() {
        EventBus.getDefault().unregister(this);
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content_frame, fragment, tag)
                .commit();
    }

    @Override
    public void onEvent(InnerFragment innerFragment) {
        if (innerFragment.equals(InnerFragment.SECOND)) {
            displaySecondFragment();
        } else if (innerFragment.equals(InnerFragment.THIRD)) {
            displayThirdFragment();
        } else {
            displayFirstFragment();
        }
        Log.d("EventBusDemo", "Fragment Change Event");
    }
}
