package com.hudsonacademy.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hudsonacademy.R;


public class AdvFilterFragment extends MyBaseFragment {

    public AdvFilterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmentView = inflater.inflate(R.layout.fragment_adv_filter, container, false);




        return fragmentView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

}
