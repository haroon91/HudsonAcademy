package com.hudsonacademy.viewholder;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hudsonacademy.R;
import com.hudsonacademy.utility.Utility;
import com.hudsonacademy.activity.TutorDetailActivity;
import com.hudsonacademy.model.Tutor;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by Haroon on 25/4/2016.
 */
public class TutorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private View mHolderView;

    private ImageView tutorImage, tutorVerified;
    private TextView tutorName, tutorSubjects, tutorPrice;
    private CardView tutorCard;
    private Tutor m_tutor;


    public TutorViewHolder(View holderView) {
        super(holderView);

        this.mHolderView = holderView;

        tutorImage = (ImageView) mHolderView.findViewById(R.id.iv_tutor);
        tutorName = (TextView) mHolderView.findViewById(R.id.tv_tutorname);
        tutorSubjects = (TextView) mHolderView.findViewById(R.id.tv_subjects);
        tutorPrice = (TextView) mHolderView.findViewById(R.id.tv_rate);
        tutorCard = (CardView) mHolderView.findViewById(R.id.cv_tutor);
        tutorVerified = (ImageView) mHolderView.findViewById(R.id.iv_verified);

        tutorCard.setOnClickListener(this);

    }

    public void setTutor(Tutor tutor) {
        this.m_tutor = tutor;

        tutorName.setText(m_tutor.name);
        tutorSubjects.setText(String.format("%s",parseTutorSubjects(m_tutor.subjects)));
        tutorPrice.setText(String.format("HK$ %d",m_tutor.hourlyRate));
        ImageLoader.getInstance().clearDiskCache();
        ImageLoader.getInstance().clearMemoryCache();
        ImageLoader.getInstance().displayImage(m_tutor.icon, tutorImage, Utility.displayImageOptions);
        tutorVerified.setVisibility(m_tutor.verified ? View.VISIBLE : View.INVISIBLE);

    }

    private String parseTutorSubjects(List<String> tutorSubjects){
        String result = "";

        if (tutorSubjects.size() == 1){
            result += tutorSubjects.get(0);
        }
        else {
            for (int i = 0; i < tutorSubjects.size(); i++) {

                if (i == tutorSubjects.size()-1) {
                    result += tutorSubjects.get(i);
                }
                else {
                    result += tutorSubjects.get(i) + ", ";
                }
            }
        }

        return result;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.cv_tutor:
//                Toast.makeText(mHolderView.getContext(), "Card clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mHolderView.getContext(), TutorDetailActivity.class);
                intent.putExtra("TUTOR_ID",m_tutor.tutorId);
                mHolderView.getContext().startActivity(intent);
                break;
        }
    }
}
