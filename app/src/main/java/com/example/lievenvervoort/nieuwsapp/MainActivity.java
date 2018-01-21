package com.example.lievenvervoort.nieuwsapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView mTitleView;
    requestHelper url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTitleView = findViewById(R.id.tv_title);

        String data = url.getAll();


        mTitleView.setText(data);

    }
}
