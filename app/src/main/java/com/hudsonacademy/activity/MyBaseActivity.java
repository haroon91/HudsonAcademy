package com.hudsonacademy.activity;

import android.app.Activity;
import android.os.Bundle;

import com.hudsonacademy.MyApp;

/**
 * Created by Haroon on 22/4/2016.
 */
public abstract class MyBaseActivity extends Activity {

    // myApp is pointing to the same Application for all activities extending this class
    private MyApp m_myApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        m_myApp = (MyApp) this.getApplication();

        m_myApp.initializeIfNeeded();

    }

    // Retrieve application object
    public MyApp getMyApp() {
        // Create if null
        if (m_myApp == null) {
            m_myApp = (MyApp) this.getApplication();
        }

        return m_myApp;
    }



}
