package com.example.eventbusdemo.witheventbus.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.witheventbus.controller.DefaultFirstViewControllerImpl;
import com.example.eventbusdemo.witheventbus.controller.FirstViewController;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class FirstFragment extends Fragment {

    private FirstViewController viewController;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (viewController == null) {
            viewController = new DefaultFirstViewControllerImpl(getActivity());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.first_fragment, container, false);
        viewController.setupViews(view);
        return view;
    }
}
