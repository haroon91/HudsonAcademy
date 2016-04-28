package com.hudsonacademy.activity;

import android.os.Bundle;
import android.app.Activity;

import com.hudsonacademy.R;

public class HotPicksFilterActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_picks_filter);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }

}
