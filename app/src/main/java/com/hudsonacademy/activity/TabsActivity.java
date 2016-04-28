package com.hudsonacademy.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v13.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.hudsonacademy.fragment.AdvFilterFragment;
import com.hudsonacademy.fragment.HotPicksFragment;
import com.hudsonacademy.R;
import com.hudsonacademy.ui.TabsBottomMenu;

public class TabsActivity extends MyBaseActivity implements TabsBottomMenu.OnTabsMenuInteractionListener {

    private static final int BOTTOM_MENU_VISIBLE = 0;
    private static final int BOTTOM_MENU_INVISIBLE = 1;

    private int tabPosition;

    private static TabsFragmentPagerAdapter mPagerAdapter;

    private ViewPager mViewPager;
    private TabsBottomMenu tabsBottomMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);

        tabsBottomMenu = new TabsBottomMenu(this);
        tabsBottomMenu.setTag(BOTTOM_MENU_VISIBLE);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        ((RelativeLayout) findViewById(R.id.ll_tabs_activity)).addView(tabsBottomMenu, params);

        // Create the adapter that will return a fragment for each of the 2 primary sections of the activity.
        mPagerAdapter = new TabsFragmentPagerAdapter(getFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.vp_tabs);
        mViewPager.setAdapter(mPagerAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("LifeCycle", "Fragment Switched");
                //action bar changed according to the fragment
                tabsBottomMenu.onTabClick(position);
                tabPosition = position;
//                customizeActionBar();
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tabPosition = mViewPager.getCurrentItem();

    }

    @Override
    public void onTabSelected(int position) {
        mViewPager.setCurrentItem(position);
    }

    public class TabsFragmentPagerAdapter extends FragmentPagerAdapter {


        public TabsFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0) {
                return new HotPicksFragment();
            } else if (position == 1) {
                return new AdvFilterFragment();
            }
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return getString(R.string.title_hotpicks);
                case 1:
                    return getString(R.string.title_advfilter);
            }
            return null;
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_tabs, container, false);
        }
    }

    //handle back button and home button the same way
    @Override
    public void onBackPressed(){
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
}
