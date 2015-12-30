package com.example.eventbusdemo.classic.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.classic.callback.FragmentChangeCallback;
import com.example.eventbusdemo.classic.callback.ToolbarColorChangeCallback;
import com.example.eventbusdemo.classic.controller.DefaultFirstViewControllerImpl;
import com.example.eventbusdemo.classic.controller.FirstViewController;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class FirstFragment extends Fragment {

    private FirstViewController viewController;

    private ToolbarColorChangeCallback colorChangeCallback;

    private FragmentChangeCallback fragmentChangeCallback;

    public static FirstFragment newInstance(ToolbarColorChangeCallback colorChangeCallback, FragmentChangeCallback fragmentChangeCallback) {
        FirstFragment fragment = new FirstFragment();
        fragment.fragmentChangeCallback = fragmentChangeCallback;
        fragment.colorChangeCallback = colorChangeCallback;
        return fragment;
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
        viewController.setupViews(view, colorChangeCallback, fragmentChangeCallback);
        return view;
    }
}
