package com.ronaldpitt.floridalaw.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.ronaldpitt.floridalaw.R;

public class StatuteActivity extends AppCompatActivity {

    TextView statuteView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statute);

        Intent intent = getIntent();
        String statuteText = intent.getStringExtra("statute");


        statuteView = (TextView) findViewById(R.id.statueView);
        statuteView.setText(statuteText);
    }
}
