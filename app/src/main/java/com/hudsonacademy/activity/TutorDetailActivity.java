package com.hudsonacademy.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hudsonacademy.R;
import com.hudsonacademy.utility.Utility;
import com.hudsonacademy.fragment.HotPicksFragment;
import com.hudsonacademy.fragment.TutorAboutFragment;
import com.hudsonacademy.fragment.TutorReviewsFragment;
import com.hudsonacademy.model.Tutor;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class TutorDetailActivity extends MyBaseActivity implements View.OnClickListener{

    private Tutor m_tutor;

    private CircleImageView tutorIcon;
    private ImageView tutorGender;
    private TextView tutorName, tutorRate, tutorLocation, tutorSummary;
    private RelativeLayout contactEmail, contactPhone, tutorVerified;

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutor_detail);

        Intent intent = getIntent();
        int tutorId = intent.getIntExtra("TUTOR_ID", 0);
        
        m_tutor = getTutor(tutorId);

        if (m_tutor == null){
            Toast.makeText(this, "No such tutor",Toast.LENGTH_SHORT).show();
            finish();
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getFragmentManager());

        tutorIcon = (CircleImageView) findViewById(R.id.civ_tutor_icon);
        tutorGender = (ImageView) findViewById(R.id.iv_gender);
        tutorName = (TextView) findViewById(R.id.tv_tutorname);
        tutorRate = (TextView) findViewById(R.id.tv_tutorrate);
        tutorLocation = (TextView) findViewById(R.id.tv_tutorlocation);
        tutorSummary = (TextView) findViewById(R.id.tv_tutor_summary);
        contactEmail = (RelativeLayout) findViewById(R.id.rl_contact_email);
        contactPhone = (RelativeLayout) findViewById(R.id.rl_contact_phone);
        tutorVerified = (RelativeLayout) findViewById(R.id.rl_verified);

        viewPager = (ViewPager) findViewById(R.id.vp_tutor_detailtabs);
        viewPager.setAdapter(mSectionsPagerAdapter);
        viewPager.setOffscreenPageLimit(1);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        tabLayout.setTabsFromPagerAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        contactEmail.setOnClickListener(this);
        contactPhone.setOnClickListener(this);

        setTutorViews(m_tutor);

    }

    private Tutor getTutor (int tutorId) {

        for (Tutor t : HotPicksFragment.mTutors) {
            if (t.tutorId == tutorId){
                return t;
            }
        }
        return  null;
    }

    synchronized public void methoda(){

    }

    private void setTutorViews (Tutor tutor) {

        ImageLoader.getInstance().displayImage(tutor.icon, tutorIcon, Utility.displayImageOptions);
        tutorGender.setImageResource(tutor.gender.equals("Male") ? R.drawable.user_male : R.drawable.user_female);
        tutorName.setText(tutor.name);
        tutorRate.setText(String.format("HK$ %d/hr", tutor.hourlyRate));
        tutorLocation.setText(String.format("%s, %s", tutor.tutorCity, tutor.tutorCountry));
//        tutorSummary.setText(tutor.summary);
        tutorVerified.setVisibility(tutor.verified ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.rl_contact_email:
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setData(Uri.parse("malito:hudsonacademyhk@gmail.com"));
                emailIntent.setType("text/plain");
                emailIntent.putExtra(Intent.EXTRA_SUBJECT,"Request email for " + m_tutor.name);

                try {
                    startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                }
                catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText(this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.rl_contact_phone:
                Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                phoneIntent.setData(Uri.parse("tel:+852-2517-8622"));
                startActivity(phoneIntent);
        }
    }

    public class SectionsPagerAdapter extends android.support.v13.app.FragmentPagerAdapter {

        public SectionsPagerAdapter(android.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public android.app.Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            if (position == 0){
                return new TutorAboutFragment();
            }
            else if (position == 1){
                return new TutorReviewsFragment();
            }

            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // 2 tabs
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Locale l = Locale.getDefault();
            switch (position) {
                case 0:
                    return "About";
                case 1:
                    return "Reviews";
            }
            return null;
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends android.app.Fragment {
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
            View rootView = inflater.inflate(R.layout.fragment_tabs, container, false);
            return rootView;
        }
    }
}
