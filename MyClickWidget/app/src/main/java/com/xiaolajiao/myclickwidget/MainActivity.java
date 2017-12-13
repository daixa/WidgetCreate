package com.xiaolajiao.myclickwidget;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView main_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        main_tv = (TextView) findViewById(R.id.main_tv);
        main_tv.setText(getIntent().getStringExtra("pending_"));

    }
}
