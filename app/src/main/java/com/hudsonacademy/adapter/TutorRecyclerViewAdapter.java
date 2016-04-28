package com.hudsonacademy.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.hudsonacademy.R;
import com.hudsonacademy.viewholder.TutorViewHolder;
import com.hudsonacademy.model.Tutor;

import java.util.List;

/**
 * Created by Haroon on 25/4/2016.
 */
public class TutorRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private List<Tutor> m_tutors;

    public TutorRecyclerViewAdapter(List<Tutor> tutors) {
        this.m_tutors = tutors;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        TutorViewHolder tutorViewHolder = new TutorViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_tutor, viewGroup, false));
        return tutorViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        ((TutorViewHolder) viewHolder).setTutor(m_tutors.get(position));
    }

    @Override
    public int getItemCount() {
        return m_tutors.size();
    }
}
