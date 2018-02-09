package com.andro.prolapso.shapi.models;

public class Exercise {
    private final int mId;
    private final String mName;
    private final String mDescription;
    private final String mMuscle;
    private final String mType;

    public Exercise(int id, String description, String name, String muscle, String type) {
        mId = id;
        mName = name;
        mMuscle = muscle;
        mDescription = description;
        mType = type;
    }

    // Getters
    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getMuscle() {
        return mMuscle;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getType() {
        return mType;
    }

}
