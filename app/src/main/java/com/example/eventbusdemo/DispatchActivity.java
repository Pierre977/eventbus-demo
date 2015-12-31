package com.example.eventbusdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.eventbusdemo.classic.MainActivityClassic;
import com.example.eventbusdemo.witheventbus.MainActivity;

/**
 * Created by petnagy on 2015. 12. 31..
 */
public class DispatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dispatch);

        Button classicButton = (Button) findViewById(R.id.classic_button);
        classicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispatchActivity.this, MainActivityClassic.class);
                startActivity(intent);
            }
        });
        Button eventBusButton = (Button) findViewById(R.id.eventbus_button);
        eventBusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DispatchActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
