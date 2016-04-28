package com.hudsonacademy.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hudsonacademy.R;

/**
 * Created by Haroon on 23/4/2016.
 */
public class TabsBottomMenu extends LinearLayout implements View.OnClickListener {

    private Drawable hotPickDrawable, advFilterDrawable;
    private TextView hotPickText, advFilterText;
    private RelativeLayout hotPickLayout, advFilterLayout;
    private View menuView;

    private PorterDuffColorFilter grayfilter, bluefilter;

    private Context mContext;

    private SparseArray<View> tabsMap = null;
    private OnTabsMenuInteractionListener mListener = null;



    public TabsBottomMenu(Context context) {
        super(context);

        mContext = context;

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        menuView = inflater.inflate(R.layout.view_tabs_menu, this, true);

        initViews(menuView);
        init();
    }

    private void init() {
        tabsMap = new SparseArray<>();
        tabsMap.put(0, hotPickLayout);
        tabsMap.put(1, advFilterLayout);

        try {
            mListener = (OnTabsMenuInteractionListener) mContext;
        } catch (ClassCastException e) {
            throw new ClassCastException(mContext.toString()
                    + " must implement OnTabsMenuInteractionListener");
        }
    }

    private void initViews(View parentView) {
        //init two color filters
        grayfilter = new PorterDuffColorFilter(getResources().getColor(R.color.app_gray), PorterDuff.Mode.MULTIPLY);
        bluefilter = new PorterDuffColorFilter(getResources().getColor(R.color.dark_purple), PorterDuff.Mode.MULTIPLY);

        hotPickText = (TextView) parentView.findViewById(R.id.tv_hotpick);
        advFilterText = (TextView) parentView.findViewById(R.id.tv_advfilter);

        hotPickDrawable = getResources().getDrawable(R.drawable.hotpick);
        advFilterDrawable = getResources().getDrawable(R.drawable.advfilter);

        ImageView hotPickView = (ImageView) parentView.findViewById(R.id.iv_hotpick);
        ImageView advFilterView = (ImageView) parentView.findViewById(R.id.iv_advfilter);

        hotPickView.setImageDrawable(hotPickDrawable);
        advFilterView.setImageDrawable(advFilterDrawable);

        //Change color to default icon selected status
        switchIconColor(0);
        switchActionBarText(0);

        hotPickLayout = (RelativeLayout) parentView.findViewById(R.id.hotpick_tab);
        advFilterLayout = (RelativeLayout) parentView.findViewById(R.id.advfilter_tab);

        hotPickLayout.setOnClickListener(this);
        advFilterLayout.setOnClickListener(this);
    }

    private void switchIconColor(int id) {
        switch (id) {

            case 0:
                advFilterText.setTextColor(getResources().getColor(R.color.app_gray));
                hotPickText.setTextColor(getResources().getColor(R.color.dark_purple));
                advFilterDrawable.mutate().setColorFilter(grayfilter);
                hotPickDrawable.mutate().setColorFilter(bluefilter);
                break;

            case 1:
                advFilterText.setTextColor(getResources().getColor(R.color.dark_purple));
                hotPickText.setTextColor(getResources().getColor(R.color.app_gray));
                advFilterDrawable.mutate().setColorFilter(bluefilter);
                hotPickDrawable.mutate().setColorFilter(grayfilter);
                break;

            default:
                break;
        }
    }

    // Update ActionBar text
    private void switchActionBarText(int id) {
        ActionBar actionBar = ((Activity)mContext).getActionBar();

        if ( actionBar == null ) {
            return;
        }

        String actionBarText = "";

        switch (id) {
            case 0:
                actionBarText = " " + getResources().getString((R.string.title_hotpicks));
                break;

            case 1:
                actionBarText = " " + getResources().getString(R.string.title_advfilter);
                break;

            default:
                break;
        }

        actionBar.setTitle(actionBarText);
    }

    public void onTabClick(int position) {
        switchIconColor(position);
        switchActionBarText(position);
    }

    @Override
    public void onClick(View view) {
        int key = tabsMap.indexOfValue(view);
        switchIconColor(key);
        switchActionBarText(key);
        mListener.onTabSelected(key);
    }

    public interface OnTabsMenuInteractionListener {
        void onTabSelected(int position);
    }
}
