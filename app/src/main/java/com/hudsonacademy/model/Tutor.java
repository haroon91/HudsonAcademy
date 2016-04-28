package com.hudsonacademy.model;

import java.util.List;

/**
 * Created by Haroon on 25/4/2016.
 */
public class Tutor {

    public int tutorId;
    public String name;
    public String description;
    public String summary;
    public List<String> subjects;
    public String graduatingSchool;
    public String degreeType;
    public String degreeFull;
    public int yrsExperience;
    public String birthday;
    public String gender;
    public String tutorCity;
    public String tutorCountry;
    public String icon;
    public int hourlyRate;
    public boolean verified;

    public Tutor (int tutorId, String name, List<String> subjects, String icon, int rate, String tutorCity,
                    String tutorCountry, String gender, boolean verified) {
        this.tutorId = tutorId;
        this.name = name;
        this.subjects = subjects;
        this.icon = icon;
        this.hourlyRate = rate;
        this.tutorCity = tutorCity;
        this.tutorCountry = tutorCountry;
        this.gender = gender;
        this.verified = verified;
    }


}
