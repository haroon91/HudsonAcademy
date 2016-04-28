package com.hudsonacademy.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hudsonacademy.R;
import com.hudsonacademy.adapter.TutorRecyclerViewAdapter;
import com.hudsonacademy.model.Tutor;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Haroon on 24/4/2016.
 */
public class HotPicksFragment extends MyBaseFragment {

    private TutorRecyclerViewAdapter mRecyclerViewAdapter;
    private StaggeredGridLayoutManager gaggeredGridLayoutManager;

    public HotPicksFragment(){}

    public static List<Tutor> mTutors = new ArrayList<Tutor>();

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstaceState) {

        View fragmentView = inflater.inflate(R.layout.fragment_hotpicks, container, false);

        RecyclerView mRecyclerView = (RecyclerView) fragmentView.findViewById(R.id.rv_tutors);
        mRecyclerViewAdapter = new TutorRecyclerViewAdapter(mTutors);

        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        mRecyclerView.setLayoutManager(gaggeredGridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);

        setTutors();

        return fragmentView;
    }

    //TODO tutors fetched from the server (in the future)
    private void setTutors() {

        mTutors.clear();

        List<String> subject1 = new ArrayList<>();
        List<String> subject2 = new ArrayList<>();

        subject1.add("Maths"); subject1.add("Physics");
        subject2.add("Accounting"); subject2.add("Economics"); subject2.add("Marketing");

        mTutors.add(new Tutor(1, "Emma Gale",subject1, "drawable://"+R.drawable.image, 300, "Shenzhen", "China", "Female",true));
        mTutors.add(new Tutor(2, "Ruby Sachs",subject2, "drawable://"+R.drawable.image2, 430, "Hong Kong", "Hong Kong", "Female",false));
        mTutors.add(new Tutor(3, "Robin Mark", subject1, "drawable://" + R.drawable.image3, 1000, "Guangzhou", "China", "Male", true));
        mTutors.add(new Tutor(4, "Pam Watson", subject1, "drawable://" + R.drawable.image4, 125, "Shanghai", "China", "Female", false));
        mTutors.add(new Tutor(5, "Taylor Tony", subject2, "drawable://" + R.drawable.image5, 800, "Bejing", "China", "Male", false));

        mRecyclerViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume () {
        super.onResume();

        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().clearDiskCache();

    }

}
