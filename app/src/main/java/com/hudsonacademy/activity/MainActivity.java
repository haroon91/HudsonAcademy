package com.hudsonacademy.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.RelativeLayout;

import com.hudsonacademy.adapter.ImagePagerAdapter;
import com.hudsonacademy.R;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends MyBaseActivity implements View.OnClickListener {

    private List<String> mTutorialPages = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO better design these two pages graphically
        mTutorialPages.add("drawable://" + R.drawable.tutorial_1);
        mTutorialPages.add("drawable://" + R.drawable.tutorial_2);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_tutorial);
        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.page_indicator);

        RelativeLayout buttonLayout = (RelativeLayout) findViewById(R.id.rl_startbutton);
        buttonLayout.setOnClickListener(this);

        ImagePagerAdapter adsViewPagerAdapter = new ImagePagerAdapter(this, mTutorialPages);
        viewPager.setAdapter(adsViewPagerAdapter);
        circlePageIndicator.setViewPager(viewPager);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.rl_startbutton:
                Intent intent = new Intent(this, TabsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
