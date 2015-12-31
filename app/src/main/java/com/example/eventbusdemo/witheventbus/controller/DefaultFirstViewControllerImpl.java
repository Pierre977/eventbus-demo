package com.example.eventbusdemo.witheventbus.controller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.eventbusdemo.R;
import com.example.eventbusdemo.dataset.Color;
import com.example.eventbusdemo.witheventbus.adapter.DefaultRecyclerViewAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by petnagy on 2015. 12. 30..
 */
public class DefaultFirstViewControllerImpl implements FirstViewController {

    private Context context;

    public DefaultFirstViewControllerImpl(Context context) {
        this.context = context;
    }

    @Override
    public void setupViews(View view) {
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        DefaultRecyclerViewAdapter adapter = new DefaultRecyclerViewAdapter(generateColorList());
        recyclerView.setAdapter(adapter);
    }

    private List<Color> generateColorList() {
        List<Color> colorList = new LinkedList<>();
        colorList.add(Color.BLACK);
        colorList.add(Color.WHITE);
        colorList.add(Color.BLUE);
        colorList.add(Color.YELLOW);
        return colorList;
    }
}
