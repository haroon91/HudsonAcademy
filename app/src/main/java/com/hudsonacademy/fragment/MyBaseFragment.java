package com.hudsonacademy.fragment;

import android.app.Activity;
import android.util.Log;

import com.hudsonacademy.activity.MyBaseActivity;

/**
 * Created by Haroon on 24/4/2016.
 */
public abstract class MyBaseFragment extends android.app.Fragment {

    // Activity to which isAttached
    private MyBaseActivity m_activity;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        Log.v("FragmentLifeCycle", this + " onAttach");

        m_activity = (MyBaseActivity) activity; // Keep reference to its attached activity
    }

    public MyBaseActivity getMyActivity() {
        return m_activity;
    }

}
