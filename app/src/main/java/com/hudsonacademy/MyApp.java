package com.hudsonacademy;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.hudsonacademy.utility.Utility;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.utils.StorageUtils;

import java.io.File;

/**
 * Created by Haroon on 22/4/2016.
 */
public class MyApp extends Application {

    private static final String TAG = "MyApp";

    private Context mContext;
    private boolean inited = false;

    private Activity currentActivity = null;

    @Override
    public void onCreate() {
        super.onCreate();

        mContext = getApplicationContext();

    }

    public Activity getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(Activity currentActivity) {
        this.currentActivity = currentActivity;
    }

    public void initializeIfNeeded() {
        if (!inited) {

            //init for image loader
            File cacheDir = StorageUtils.getCacheDirectory(this);
            ImageLoaderConfiguration config = (new ImageLoaderConfiguration.Builder(this))
                    .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
                    .memoryCacheSize(2 * 1024 * 1024)
                    .diskCache(new UnlimitedDiscCache(cacheDir))
                    .diskCacheSize(50 * 1024 * 1024)
                    .diskCacheFileCount(100)
                    .build();
            ImageLoader.getInstance().init(config);

            //If fetch image fails, show the white bird icon
            Utility.displayImageOptions = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
//                    .showImageForEmptyUri(R.drawable.icon)
//                    .showImageOnFail(R.drawable.icon)
                    .build();

            //If fetch image fails, show the default empty icon
            Utility.userIconOptions = new DisplayImageOptions.Builder()
                    .cacheInMemory(true)
                    .cacheOnDisk(true)
//                    .showImageForEmptyUri(R.drawable.user_icon_anonymous)
//                    .showImageOnFail(R.drawable.user_icon_anonymous)
                    .build();

            inited = true;
        }
    }
}
