package com.hudsonacademy.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hudsonacademy.utility.Utility;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Haroon on 22/4/2016.
 */
public class ImagePagerAdapter extends PagerAdapter {

    private List<String> imageList;

    private Context context;

    private OnClickItemListener listener;

    public ImagePagerAdapter(Context context, List<String> newImageList){
        super();
        this.context = context;
        this.imageList = newImageList;
    }
    public ImagePagerAdapter(Context context, List<String> newImageList, OnClickItemListener listener){
        super();
        this.context = context;
        this.imageList = newImageList;
        this.listener = listener;
    }



    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {

        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(context);
        imageView.setAdjustViewBounds(true);

        ImageLoader.getInstance().displayImage(imageList.get(position), imageView, Utility.displayImageOptions);
        container.addView(imageView, 0);

        if (listener != null){
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onClickItem(position);
                }
            });
        }
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
    public interface OnClickItemListener{
        void onClickItem(int position);
    }
}