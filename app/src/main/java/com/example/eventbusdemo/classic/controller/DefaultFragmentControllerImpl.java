package com.example.eventbusdemo.classic.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.callback.FragmentChangeCallback;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;
import com.example.eventbusdemo.classic.fragments.FirstFragment;
import com.example.eventbusdemo.classic.fragments.SecondFragment;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultFragmentControllerImpl implements FragmentController, FragmentChangeCallback {

    private static final String TAG_FIRST = "firstFragment";

    private static final String TAG_SECOND = "secondFragment";

    private FragmentManager fragmentManager;

    private ToolbarColorChangeCallback colorChangeCallback;

    public DefaultFragmentControllerImpl(FragmentManager fragmentManager, ToolbarColorChangeCallback colorChangeCallback) {
        this.fragmentManager = fragmentManager;
        this.colorChangeCallback = colorChangeCallback;
    }

    @Override
    public void displayFirstFragment() {
        replaceFragment(FirstFragment.newInstance(colorChangeCallback, this), TAG_FIRST);
    }

    @Override
    public void displaySecondFragment() {
        replaceFragment(SecondFragment.newInstance(), TAG_SECOND);
    }

    @Override
    public boolean isSecondFragmentActive() {
        Fragment fragment = fragmentManager.findFragmentByTag(TAG_SECOND);
        return fragment != null;
    }

    private void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content_frame, fragment, tag)
                .commit();
    }

    @Override
    public void onNextFragment() {
        displaySecondFragment();
        Log.d("EventBusDemo", "Fragment Change Callback");
    }
}
