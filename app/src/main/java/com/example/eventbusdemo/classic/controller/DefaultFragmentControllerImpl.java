package com.example.eventbusdemo.classic.controller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.callback.FragmentChangeCallback;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;
import com.example.eventbusdemo.classic.fragments.FirstFragment;
import com.example.eventbusdemo.classic.fragments.SecondFragment;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultFragmentControllerImpl implements FragmentController, FragmentChangeCallback {

    private static final String TAG_CONTENT = "tagContent";

    private FragmentManager fragmentManager;

    private ToolbarColorChangeCallback colorChangeCallback;

    public DefaultFragmentControllerImpl(FragmentManager fragmentManager, ToolbarColorChangeCallback colorChangeCallback) {
        this.fragmentManager = fragmentManager;
        this.colorChangeCallback = colorChangeCallback;
    }

    @Override
    public void displayFirstFragment() {
        replaceFragment(FirstFragment.newInstance(colorChangeCallback, this));
    }

    @Override
    public void displaySecondFragment() {
        replaceFragment(SecondFragment.newInstance());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(R.id.content_frame, fragment, TAG_CONTENT)
                .commit();
    }

    @Override
    public void onNextFragment() {
        displaySecondFragment();
    }
}
